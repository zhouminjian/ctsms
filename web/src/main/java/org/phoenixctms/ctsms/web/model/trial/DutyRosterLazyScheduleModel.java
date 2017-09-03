package org.phoenixctms.ctsms.web.model.trial;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.phoenixctms.ctsms.compare.DutyRosterTurnIntervalScheduleComparator;
import org.phoenixctms.ctsms.compare.InventoryBookingIntervalScheduleComparator;
import org.phoenixctms.ctsms.compare.ProbandStatusEntryIntervalScheduleComparator;
import org.phoenixctms.ctsms.compare.StaffStatusEntryIntervalScheduleComparator;
import org.phoenixctms.ctsms.compare.VisitScheduleItemIntervalScheduleComparator;
import org.phoenixctms.ctsms.enumeration.VariablePeriod;
import org.phoenixctms.ctsms.exception.AuthenticationException;
import org.phoenixctms.ctsms.exception.AuthorisationException;
import org.phoenixctms.ctsms.exception.ServiceException;
import org.phoenixctms.ctsms.vo.AuthenticationVO;
import org.phoenixctms.ctsms.vo.CourseOutVO;
import org.phoenixctms.ctsms.vo.DutyRosterTurnOutVO;
import org.phoenixctms.ctsms.vo.HolidayVO;
import org.phoenixctms.ctsms.vo.InventoryBookingOutVO;
import org.phoenixctms.ctsms.vo.ProbandStatusEntryOutVO;
import org.phoenixctms.ctsms.vo.StaffOutVO;
import org.phoenixctms.ctsms.vo.StaffStatusEntryOutVO;
import org.phoenixctms.ctsms.vo.TimelineEventOutVO;
import org.phoenixctms.ctsms.vo.VisitScheduleItemOutVO;
import org.phoenixctms.ctsms.web.model.LazyScheduleModelBase;
import org.phoenixctms.ctsms.web.model.shared.CollidingInventoryBookingEagerModel;
import org.phoenixctms.ctsms.web.model.shared.CollidingStaffStatusEntryEagerModel;
import org.phoenixctms.ctsms.web.model.shared.CourseEvent;
import org.phoenixctms.ctsms.web.model.shared.HolidayEvent;
import org.phoenixctms.ctsms.web.model.shared.ProbandStatusEvent;
import org.phoenixctms.ctsms.web.util.DateUtil;
import org.phoenixctms.ctsms.web.util.DefaultSettings;
import org.phoenixctms.ctsms.web.util.SettingCodes;
import org.phoenixctms.ctsms.web.util.Settings;
import org.phoenixctms.ctsms.web.util.Settings.Bundle;
import org.phoenixctms.ctsms.web.util.WebUtil;

public class DutyRosterLazyScheduleModel extends LazyScheduleModelBase {

	private HashMap<Long, CollidingStaffStatusEntryEagerModel> collidingStaffStatusEntryModelCache;
	private HashMap<Long, CollidingInventoryBookingEagerModel> collidingInventoryBookingModelCache;
	private boolean showHolidays;
	private boolean showDutyRoster;
	private boolean showStaffStatus;
	private boolean showCourseBookings;
	private boolean showVisitSchedule;
	private boolean showTimelineEvents;
	private boolean showCourses;
	private boolean showProbandStatus;
	private Long showCollisionsThresholdDays;
	private Long departmentId;
	private Long staffCategoryId;
	private Long trialId;
	private String calendar;
	private Long teamMemberStaffId;
	private Boolean notify;
	private Boolean ignoreTimelineEvents;
	private Long courseCategoryId;
	private Long probandCategoryId;
	private Long statusId;
	private Long visitTypeId;
	private Boolean hideProbandAvailability;
	private Boolean hideStaffAvailability;

	public DutyRosterLazyScheduleModel(HashMap<Long, CollidingStaffStatusEntryEagerModel> collidingStaffStatusEntryModelCache,
			HashMap<Long, CollidingInventoryBookingEagerModel> collidingInventoryBookingModelCache) {
		super();
		this.collidingStaffStatusEntryModelCache = collidingStaffStatusEntryModelCache;
		this.collidingInventoryBookingModelCache = collidingInventoryBookingModelCache;
		showHolidays = Settings.getBoolean(SettingCodes.DUTY_ROSTER_SCHEDULE_SHOW_HOLIDAYS_PRESET, Bundle.SETTINGS, DefaultSettings.DUTY_ROSTER_SCHEDULE_SHOW_HOLIDAYS_PRESET);
		showDutyRoster = Settings.getBoolean(SettingCodes.DUTY_ROSTER_SCHEDULE_SHOW_DUTY_ROSTER_PRESET, Bundle.SETTINGS,
				DefaultSettings.DUTY_ROSTER_SCHEDULE_SHOW_DUTY_ROSTER_PRESET);
		showStaffStatus = Settings.getBoolean(SettingCodes.DUTY_ROSTER_SCHEDULE_SHOW_STAFF_STATUS_PRESET, Bundle.SETTINGS,
				DefaultSettings.DUTY_ROSTER_SCHEDULE_SHOW_STAFF_STATUS_PRESET);
		showCourseBookings = Settings.getBoolean(SettingCodes.DUTY_ROSTER_SCHEDULE_SHOW_COURSE_BOOKINGS_PRESET, Bundle.SETTINGS,
				DefaultSettings.DUTY_ROSTER_SCHEDULE_SHOW_COURSE_BOOKINGS_PRESET);
		showVisitSchedule = Settings.getBoolean(SettingCodes.DUTY_ROSTER_SCHEDULE_SHOW_VISIT_SCHEDULE_PRESET, Bundle.SETTINGS,
				DefaultSettings.DUTY_ROSTER_SCHEDULE_SHOW_VISIT_SCHEDULE_PRESET);
		showTimelineEvents = Settings.getBoolean(SettingCodes.DUTY_ROSTER_SCHEDULE_SHOW_TIMELINE_EVENTS_PRESET, Bundle.SETTINGS,
				DefaultSettings.DUTY_ROSTER_SCHEDULE_SHOW_TIMELINE_EVENTS_PRESET);
		showCourses = Settings.getBoolean(SettingCodes.DUTY_ROSTER_SCHEDULE_SHOW_COURSES_PRESET, Bundle.SETTINGS, DefaultSettings.DUTY_ROSTER_SCHEDULE_SHOW_COURSES_PRESET);
		showProbandStatus = Settings.getBoolean(SettingCodes.DUTY_ROSTER_SCHEDULE_SHOW_PROBAND_STATUS_PRESET, Bundle.SETTINGS,
				DefaultSettings.DUTY_ROSTER_SCHEDULE_SHOW_PROBAND_STATUS_PRESET);
		showCollisionsThresholdDays = Settings.getLongNullable(SettingCodes.DUTY_ROSTER_SCHEDULE_SHOW_COLLISIONS_THRESHOLD_DAYS, Bundle.SETTINGS,
				DefaultSettings.DUTY_ROSTER_SCHEDULE_SHOW_COLLISIONS_THRESHOLD_DAYS);
		setShowNotifyOnly(Settings.getBoolean(SettingCodes.TIMELINE_SCHEDULE_SHOW_NOTIFY_ONLY_PRESET, Bundle.SETTINGS, DefaultSettings.TIMELINE_SCHEDULE_SHOW_NOTIFY_ONLY_PRESET));
		setIgnoreObsoleteTimelineEvents(Settings.getBoolean(SettingCodes.TIMELINE_SCHEDULE_IGNORE_OBSOLETE_PRESET, Bundle.SETTINGS,
				DefaultSettings.TIMELINE_SCHEDULE_IGNORE_OBSOLETE_PRESET));
		setIgnoreObsoleteProbandStatus(Settings.getBoolean(SettingCodes.PROBAND_STATUS_IGNORE_OBSOLETE_PRESET, Bundle.SETTINGS,
				DefaultSettings.PROBAND_STATUS_IGNORE_OBSOLETE_PRESET));
		setIgnoreObsoleteStaffStatus(Settings.getBoolean(SettingCodes.STAFF_STATUS_IGNORE_OBSOLETE_PRESET, Bundle.SETTINGS, DefaultSettings.STAFF_STATUS_IGNORE_OBSOLETE_PRESET));
		StaffOutVO identity = WebUtil.getUserIdentity();
		if (identity != null) {
			if (Settings.getBoolean(SettingCodes.DUTY_ROSTER_SCHEDULE_ACTIVE_IDENTITY_PRESET, Bundle.SETTINGS, DefaultSettings.DUTY_ROSTER_SCHEDULE_ACTIVE_IDENTITY_PRESET)) {
				setTeamMemberStaffId(identity.getId());
			}
			if (Settings.getBoolean(SettingCodes.DUTY_ROSTER_SCHEDULE_ACTIVE_IDENTITY_DEPARTMENT_PRESET, Bundle.SETTINGS,
					DefaultSettings.DUTY_ROSTER_SCHEDULE_ACTIVE_IDENTITY_DEPARTMENT_PRESET)) {
				setDepartmentId(identity.getDepartment().getId());
			}
		}
	}

	@Override
	public void clearCaches() {
		collidingStaffStatusEntryModelCache.clear();
		collidingInventoryBookingModelCache.clear();
	}

	public String getCalendar() {
		return calendar;
	}

	public Long getCourseCategoryId() {
		return courseCategoryId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public boolean getIgnoreObsoleteProbandStatus() {
		return hideProbandAvailability != null;
	}

	public boolean getIgnoreObsoleteStaffStatus() {
		return hideStaffAvailability != null;
	}

	public boolean getIgnoreObsoleteTimelineEvents() {
		return ignoreTimelineEvents != null;
	}

	@Override
	protected void getLazyResult(Date start, Date end) {
		Date from = DateUtil.sanitizeScheduleTimestamp(true, start);
		Date to = DateUtil.sanitizeScheduleTimestamp(true, stop);
		AuthenticationVO auth = WebUtil.getAuthentication();
		if (showHolidays) {
			Collection<HolidayVO> holidays = null;
			try {
				holidays = WebUtil.getServiceLocator().getToolsService()
						.getHolidays(auth, from, to, Settings.getBooleanNullable(SettingCodes.SHOW_HOLIDAYS, Bundle.SETTINGS, DefaultSettings.SHOW_HOLIDAYS));
			} catch (ServiceException e) {
			} catch (AuthenticationException e) {
				WebUtil.publishException(e);
			} catch (AuthorisationException e) {
			} catch (IllegalArgumentException e) {
			}
			if (holidays != null) {
				Iterator<HolidayVO> it = holidays.iterator();
				while (it.hasNext()) {
					addEvent(new HolidayEvent(it.next()));
				}
			}
		}
		if (showDutyRoster) {
			Collection<DutyRosterTurnOutVO> dutyRoster = null;
			try {
				dutyRoster = WebUtil.getServiceLocator().getTrialService().getDutyRosterInterval(auth, departmentId, statusId, teamMemberStaffId,
						Settings.getBoolean(SettingCodes.DUTY_ROSTER_SCHEDULE_SHOW_UNASSIGED_DUTIES, Bundle.SETTINGS, DefaultSettings.DUTY_ROSTER_SCHEDULE_SHOW_UNASSIGED_DUTIES),
						trialId, calendar, from, to, false);
			} catch (ServiceException e) {
			} catch (AuthenticationException e) {
				WebUtil.publishException(e);
			} catch (AuthorisationException e) {
			} catch (IllegalArgumentException e) {
			}
			if (dutyRoster != null) {
				dutyRoster = new ArrayList(dutyRoster);
				Collections.sort((ArrayList) dutyRoster, new DutyRosterTurnIntervalScheduleComparator(false));
				// xCollections.reverse((ArrayList) dutyRoster);
				boolean showCollisions = (showCollisionsThresholdDays == null ? true : to.compareTo(WebUtil.addIntervals(from, VariablePeriod.EXPLICIT,
						showCollisionsThresholdDays, 1)) <= 0);
				Iterator<DutyRosterTurnOutVO> it = dutyRoster.iterator();
				while (it.hasNext()) {
					DutyRosterTurnOutVO dutyRosterTurn = it.next();
					// System.out.println(dutyRosterTurn.getStart() + " " + (dutyRosterTurn.getStaff() != null ? dutyRosterTurn.getStaff().getName() : ""));
					DutyRosterTurnEvent event = new DutyRosterTurnEvent(dutyRosterTurn);
					event.setCollidingStaffStatusEntryCount(CollidingStaffStatusEntryEagerModel.getCachedCollidingStaffStatusEntryModel(dutyRosterTurn, showCollisions,
							collidingStaffStatusEntryModelCache).getAllRowCount());
					event.setCollidingInventoryBookingCount(CollidingInventoryBookingEagerModel.getCachedCollidingInventoryBookingModel(dutyRosterTurn, showCollisions,
							collidingInventoryBookingModelCache).getAllRowCount());
					addEvent(event);
				}
			}
		}
		if (showStaffStatus) {
			Collection<StaffStatusEntryOutVO> statusEntries = null;
			try {
				statusEntries = WebUtil.getServiceLocator().getStaffService()
						.getStaffStatusEntryInterval(auth, departmentId, staffCategoryId, hideStaffAvailability, from, to, false);
			} catch (ServiceException e) {
			} catch (AuthenticationException e) {
				WebUtil.publishException(e);
			} catch (AuthorisationException e) {
			} catch (IllegalArgumentException e) {
			}
			if (statusEntries != null) {
				statusEntries = new ArrayList(statusEntries);
				Collections.sort((ArrayList) statusEntries, new StaffStatusEntryIntervalScheduleComparator(false));
				Iterator<StaffStatusEntryOutVO> it = statusEntries.iterator();
				while (it.hasNext()) {
					addEvent(new StaffStatusEvent(it.next()));
				}
			}
		}
		if (showCourseBookings) {
			Collection<InventoryBookingOutVO> bookings = null;
			try {
				bookings = WebUtil
						.getServiceLocator()
						.getCourseService()
						.getCourseInventoryBookingParticipantInterval(
								auth,
								Settings.getBoolean(SettingCodes.DUTY_ROSTER_SCHEDULE_SHOW_ALL_COURSE_INVENTORY_BOOKINGS, Bundle.SETTINGS,
										DefaultSettings.DUTY_ROSTER_SCHEDULE_SHOW_ALL_COURSE_INVENTORY_BOOKINGS) ? null : teamMemberStaffId,
												departmentId, courseCategoryId, from, to, true, false);
			} catch (ServiceException e) {
			} catch (AuthenticationException e) {
				WebUtil.publishException(e);
			} catch (AuthorisationException e) {
			} catch (IllegalArgumentException e) {
			}
			if (bookings != null) {
				bookings = new ArrayList(bookings);
				Collections.sort((ArrayList) bookings, new InventoryBookingIntervalScheduleComparator(false));
				Iterator<InventoryBookingOutVO> it = bookings.iterator();
				while (it.hasNext()) {
					addEvent(new CourseInventoryBookingEvent(it.next()));
				}
			}
		}
		if (showVisitSchedule) {
			Collection<VisitScheduleItemOutVO> visitScheduleItems = null;
			try {
				visitScheduleItems = WebUtil.getServiceLocator().getTrialService()
						.getVisitScheduleItemInterval(auth, trialId, departmentId, statusId, visitTypeId, from, to, null, false);
			} catch (ServiceException e) {
			} catch (AuthenticationException e) {
				WebUtil.publishException(e);
			} catch (AuthorisationException e) {
			} catch (IllegalArgumentException e) {
			}
			if (visitScheduleItems != null) {
				visitScheduleItems = new ArrayList(visitScheduleItems);
				Collections.sort((ArrayList) visitScheduleItems, new VisitScheduleItemIntervalScheduleComparator(false));
				Iterator<VisitScheduleItemOutVO> it = visitScheduleItems.iterator();
				while (it.hasNext()) {
					addEvent(new VisitScheduleItemEvent(it.next()));
				}
			}
		}
		if (showTimelineEvents) {
			Collection<TimelineEventOutVO> timelineEvents = null;
			try {
				timelineEvents = WebUtil.getServiceLocator().getTrialService()
						.getTimelineInterval(auth, trialId, departmentId, teamMemberStaffId, notify, ignoreTimelineEvents, from, to);
			} catch (ServiceException e) {
			} catch (AuthenticationException e) {
				WebUtil.publishException(e);
			} catch (AuthorisationException e) {
			} catch (IllegalArgumentException e) {
			}
			if (timelineEvents != null) {
				Iterator<TimelineEventOutVO> it = timelineEvents.iterator();
				while (it.hasNext()) {
					addEvent(new TimelineEvent(it.next()));
				}
			}
		}
		if (showCourses) {
			Collection<CourseOutVO> courses = null;
			try {
				courses = WebUtil.getServiceLocator().getCourseService().getCourseInterval(auth, departmentId, courseCategoryId, from, to);
			} catch (ServiceException e) {
			} catch (AuthenticationException e) {
				WebUtil.publishException(e);
			} catch (AuthorisationException e) {
			} catch (IllegalArgumentException e) {
			}
			if (courses != null) {
				Iterator<CourseOutVO> it = courses.iterator();
				while (it.hasNext()) {
					addEvent(new CourseEvent(it.next()));
				}
			}
		}
		if (showProbandStatus) {
			Collection<ProbandStatusEntryOutVO> statusEntries = null;
			try {
				statusEntries = WebUtil.getServiceLocator().getProbandService()
						.getProbandStatusEntryInterval(auth, departmentId, probandCategoryId, hideProbandAvailability, from, to, false);
			} catch (ServiceException e) {
			} catch (AuthenticationException e) {
				WebUtil.publishException(e);
			} catch (AuthorisationException e) {
			} catch (IllegalArgumentException e) {
			}
			if (statusEntries != null) {
				statusEntries = new ArrayList(statusEntries);
				Collections.sort((ArrayList) statusEntries, new ProbandStatusEntryIntervalScheduleComparator(false));
				Iterator<ProbandStatusEntryOutVO> it = statusEntries.iterator();
				while (it.hasNext()) {
					addEvent(new ProbandStatusEvent(it.next()));
				}
			}
		}
	}

	public Long getProbandCategoryId() {
		return probandCategoryId;
	}

	public boolean getShowNotifyOnly() {
		return notify != null;
	}

	public Long getStaffCategoryId() {
		return staffCategoryId;
	}

	public Long getStatusId() {
		return statusId;
	}

	public Long getTeamMemberStaffId() {
		return teamMemberStaffId;
	}

	public String getTeamMemberStaffName() {
		return WebUtil.staffIdToName(teamMemberStaffId);
	}

	public Long getTrialId() {
		return trialId;
	}

	public String getTrialName() {
		return WebUtil.trialIdToName(trialId);
	}

	public Long getVisitTypeId() {
		return visitTypeId;
	}

	public boolean isShowCourseBookings() {
		return showCourseBookings;
	}

	public boolean isShowCourses() {
		return showCourses;
	}

	public boolean isShowDutyRoster() {
		return showDutyRoster;
	}

	public boolean isShowHolidays() {
		return showHolidays;
	}

	public boolean isShowProbandStatus() {
		return showProbandStatus;
	}

	public boolean isShowStaffStatus() {
		return showStaffStatus;
	}

	public boolean isShowTimelineEvents() {
		return showTimelineEvents;
	}

	public boolean isShowVisitSchedule() {
		return showVisitSchedule;
	}

	public void setCalendar(String calendar) {
		this.calendar = calendar;
	}

	public void setCourseCategoryId(Long courseCategoryId) {
		this.courseCategoryId = courseCategoryId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public void setIgnoreObsoleteProbandStatus(boolean ignoreObsolete) {
		this.hideProbandAvailability = ignoreObsolete ? false : null;
	}

	public void setIgnoreObsoleteStaffStatus(boolean ignoreObsolete) {
		this.hideStaffAvailability = ignoreObsolete ? false : null;
	}

	public void setIgnoreObsoleteTimelineEvents(boolean ignoreObsolete) {
		this.ignoreTimelineEvents = ignoreObsolete ? false : null;
	}

	public void setProbandCategoryId(Long probandCategoryId) {
		this.probandCategoryId = probandCategoryId;
	}

	public void setShowCourseBookings(boolean showCourseBookings) {
		this.showCourseBookings = showCourseBookings;
	}

	public void setShowCourses(boolean showCourses) {
		this.showCourses = showCourses;
	}

	public void setShowDutyRoster(boolean showDutyRoster) {
		this.showDutyRoster = showDutyRoster;
	}

	public void setShowHolidays(boolean showHolidays) {
		this.showHolidays = showHolidays;
	}

	public void setShowNotifyOnly(boolean notify) {
		this.notify = notify ? true : null;
	}

	public void setShowProbandStatus(boolean showProbandStatus) {
		this.showProbandStatus = showProbandStatus;
	}

	public void setShowStaffStatus(boolean showStaffStatus) {
		this.showStaffStatus = showStaffStatus;
	}

	public void setShowTimelineEvents(boolean showTimelineEvents) {
		this.showTimelineEvents = showTimelineEvents;
	}

	public void setShowVisitSchedule(boolean showVisitSchedule) {
		this.showVisitSchedule = showVisitSchedule;
	}

	public void setStaffCategoryId(Long staffId) {
		this.staffCategoryId = staffId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public void setTeamMemberStaffId(Long teamMemberStaffId) {
		this.teamMemberStaffId = teamMemberStaffId;
	}

	public void setTrialId(Long trialId) {
		this.trialId = trialId;
	}

	public void setVisitTypeId(Long visitTypeId) {
		this.visitTypeId = visitTypeId;
	}
}
