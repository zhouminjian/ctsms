package org.phoenixctms.ctsms.excel;

import java.util.ArrayList;

public final class ProbandListExcelDefaultSettings {

	public static final int VO_GRAPH_RECURSION_DEPTH = 3; // 2;
	public static final String PROBAND_LIST_SPREADSHEET_NAME = null;
	public static final String ENROLLMENT_LOG_SPREADSHEET_NAME = null;
	public static final String PRE_SCREENING_LOG_SPREADSHEET_NAME = null;
	public static final String SCREENING_LOG_SPREADSHEET_NAME = null;
	public static final String SICL_SPREADSHEET_NAME = null;
	public static final String PROBAND_LIST_TEMPLATE_FILE_NAME = null;
	public static final boolean PROBAND_LIST_AUTOSIZE = true;
	public static final boolean PROBAND_LIST_WRITEHEAD = true;
	public static final Integer PROBAND_LIST_PAGE_BREAK_AT_ROW = null;
	public static final String ENROLLMENT_LOG_TEMPLATE_FILE_NAME = null;
	public static final boolean ENROLLMENT_LOG_AUTOSIZE = true;
	public static final boolean ENROLLMENT_LOG_WRITEHEAD = true;
	public static final Integer ENROLLMENT_LOG_PAGE_BREAK_AT_ROW = null;
	public static final String SCREENING_LOG_TEMPLATE_FILE_NAME = null;
	public static final boolean SCREENING_LOG_AUTOSIZE = true;
	public static final boolean SCREENING_LOG_WRITEHEAD = true;
	public static final Integer SCREENING_LOG_PAGE_BREAK_AT_ROW = null;
	public static final String PRE_SCREENING_LOG_TEMPLATE_FILE_NAME = null;
	public static final boolean PRE_SCREENING_LOG_AUTOSIZE = true;
	public static final boolean PRE_SCREENING_LOG_WRITEHEAD = true;
	public static final Integer PRE_SCREENING_LOG_PAGE_BREAK_AT_ROW = null;
	public static final String SICL_TEMPLATE_FILE_NAME = null;
	public static final boolean SICL_AUTOSIZE = true;
	public static final boolean SICL_WRITEHEAD = true;
	public static final Integer SICL_PAGE_BREAK_AT_ROW = null;
	public static final Integer PROBAND_LIST_SCALE_FACTOR = null;
	public static final Integer ENROLLMENT_LOG_SCALE_FACTOR = null;
	public static final Integer SCREENING_LOG_SCALE_FACTOR = null;
	public static final Integer PRE_SCREENING_LOG_SCALE_FACTOR = null;
	public static final Integer SICL_SCALE_FACTOR = null;
	public static final ExcelCellFormat PROBAND_LIST_HEAD_FORMAT = ExcelCellFormat.getDefaultHeadFormat();
	public static final ExcelCellFormat ENROLLMENT_LOG_HEAD_FORMAT = ExcelCellFormat.getDefaultHeadFormat();
	public static final ExcelCellFormat SCREENING_LOG_HEAD_FORMAT = ExcelCellFormat.getDefaultHeadFormat();
	public static final ExcelCellFormat PRE_SCREENING_LOG_HEAD_FORMAT = ExcelCellFormat.getDefaultHeadFormat();
	public static final ExcelCellFormat SICL_HEAD_FORMAT = ExcelCellFormat.getDefaultHeadFormat();
	public static final ExcelCellFormat PROBAND_LIST_ROW_FORMAT = ExcelCellFormat.getDefaultRowFormat();
	public static final ExcelCellFormat ENROLLMENT_LOG_ROW_FORMAT = ExcelCellFormat.getDefaultRowFormat();
	public static final ExcelCellFormat SCREENING_LOG_ROW_FORMAT = ExcelCellFormat.getDefaultRowFormat();
	public static final ExcelCellFormat PRE_SCREENING_LOG_ROW_FORMAT = ExcelCellFormat.getDefaultRowFormat();
	public static final ExcelCellFormat SICL_ROW_FORMAT = ExcelCellFormat.getDefaultRowFormat();
	public static final boolean PROBAND_LIST_ROW_COLORS = true;
	public static final boolean ENROLLMENT_LOG_ROW_COLORS = true;
	public static final boolean SCREENING_LOG_ROW_COLORS = true;
	public static final boolean PRE_SCREENING_LOG_ROW_COLORS = true;
	public static final boolean SICL_ROW_COLORS = true;
	public static final boolean PROBAND_LIST_APPEND_HEADER_FOOTER = false;
	public static final boolean ENROLLMENT_LOG_APPEND_HEADER_FOOTER = false;
	public static final boolean SCREENING_LOG_APPEND_HEADER_FOOTER = false;
	public static final boolean PRE_SCREENING_LOG_APPEND_HEADER_FOOTER = false;
	public static final boolean SICL_APPEND_HEADER_FOOTER = false;
	public static final boolean PROBAND_LIST_SHOW_PROBAND_LIST_ENTRY_TAGS = false;
	public static final boolean PROBAND_LIST_SHOW_ALL_PROBAND_LIST_ENTRY_TAGS = false;
	public static final boolean PROBAND_LIST_SHOW_ALL_PROBAND_LIST_ENTRY_TAG_DATES = false;
	public static final boolean PROBAND_LIST_SHOW_INQUIRIES = false;
	public static final boolean PROBAND_LIST_SHOW_ALL_INQUIRIES = false;
	public static final boolean PROBAND_LIST_SHOW_ALL_INQUIRY_DATES = false;
	public static final boolean PROBAND_LIST_SHOW_ADDRESSES = false;
	public static final boolean PROBAND_LIST_SHOW_CONTACT_DETAILS = false;
	public static final boolean PROBAND_LIST_SHOW_TAGS = false;
	public static final boolean PROBAND_LIST_SHOW_ENROLLMENT_STATUS_LOG = false;
	public static final boolean PROBAND_LIST_AGGREGATE_ADDRESSES = false;
	public static final boolean PROBAND_LIST_AGGREGATE_CONTACT_DETAILS = false;
	public static final boolean PROBAND_LIST_SHOW_IC_DATE = false;
	public static final boolean PROBAND_LIST_SHOW_IC_AGE = false;
	public static final boolean PROBAND_LIST_SHOW_SCREENING_DATE = false;
	public static final boolean PROBAND_LIST_SHOW_SCREENING_REASON = false;
	public static final boolean ENROLLMENT_LOG_SHOW_PROBAND_LIST_ENTRY_TAGS = false;
	public static final boolean ENROLLMENT_LOG_SHOW_ALL_PROBAND_LIST_ENTRY_TAGS = false;
	public static final boolean ENROLLMENT_LOG_SHOW_ALL_PROBAND_LIST_ENTRY_TAG_DATES = false;
	public static final boolean ENROLLMENT_LOG_SHOW_INQUIRIES = false;
	public static final boolean ENROLLMENT_LOG_SHOW_ALL_INQUIRIES = false;
	public static final boolean ENROLLMENT_LOG_SHOW_ALL_INQUIRY_DATES = false;
	public static final boolean ENROLLMENT_LOG_SHOW_ADDRESSES = false;
	public static final boolean ENROLLMENT_LOG_SHOW_CONTACT_DETAILS = false;
	public static final boolean ENROLLMENT_LOG_SHOW_TAGS = false;
	public static final boolean ENROLLMENT_LOG_SHOW_ENROLLMENT_STATUS_LOG = false;
	public static final boolean ENROLLMENT_LOG_AGGREGATE_ADDRESSES = false;
	public static final boolean ENROLLMENT_LOG_AGGREGATE_CONTACT_DETAILS = false;
	public static final boolean ENROLLMENT_LOG_SHOW_IC_DATE = false;
	public static final boolean ENROLLMENT_LOG_SHOW_IC_AGE = false;
	public static final boolean ENROLLMENT_LOG_SHOW_SCREENING_DATE = false;
	public static final boolean ENROLLMENT_LOG_SHOW_SCREENING_REASON = false;
	public static final boolean SCREENING_LOG_SHOW_PROBAND_LIST_ENTRY_TAGS = false;
	public static final boolean SCREENING_LOG_SHOW_ALL_PROBAND_LIST_ENTRY_TAGS = false;
	public static final boolean SCREENING_LOG_SHOW_ALL_PROBAND_LIST_ENTRY_TAG_DATES = false;
	public static final boolean SCREENING_LOG_SHOW_INQUIRIES = false;
	public static final boolean SCREENING_LOG_SHOW_ALL_INQUIRIES = false;
	public static final boolean SCREENING_LOG_SHOW_ALL_INQUIRY_DATES = false;
	public static final boolean SCREENING_LOG_SHOW_ADDRESSES = false;
	public static final boolean SCREENING_LOG_SHOW_CONTACT_DETAILS = false;
	public static final boolean SCREENING_LOG_SHOW_TAGS = false;
	public static final boolean SCREENING_LOG_SHOW_ENROLLMENT_STATUS_LOG = false;
	public static final boolean SCREENING_LOG_AGGREGATE_ADDRESSES = false;
	public static final boolean SCREENING_LOG_AGGREGATE_CONTACT_DETAILS = false;
	public static final boolean SCREENING_LOG_SHOW_IC_DATE = false;
	public static final boolean SCREENING_LOG_SHOW_IC_AGE = false;
	public static final boolean SCREENING_LOG_SHOW_SCREENING_DATE = false;
	public static final boolean SCREENING_LOG_SHOW_SCREENING_REASON = false;
	public static final boolean PRE_SCREENING_LOG_SHOW_PROBAND_LIST_ENTRY_TAGS = false;
	public static final boolean PRE_SCREENING_LOG_SHOW_ALL_PROBAND_LIST_ENTRY_TAGS = false;
	public static final boolean PRE_SCREENING_LOG_SHOW_ALL_PROBAND_LIST_ENTRY_TAG_DATES = false;
	public static final boolean PRE_SCREENING_LOG_SHOW_INQUIRIES = false;
	public static final boolean PRE_SCREENING_LOG_SHOW_ALL_INQUIRIES = false;
	public static final boolean PRE_SCREENING_LOG_SHOW_ALL_INQUIRY_DATES = false;
	public static final boolean PRE_SCREENING_LOG_SHOW_ADDRESSES = false;
	public static final boolean PRE_SCREENING_LOG_SHOW_CONTACT_DETAILS = false;
	public static final boolean PRE_SCREENING_LOG_SHOW_TAGS = false;
	public static final boolean PRE_SCREENING_LOG_SHOW_ENROLLMENT_STATUS_LOG = false;
	public static final boolean PRE_SCREENING_LOG_AGGREGATE_ADDRESSES = false;
	public static final boolean PRE_SCREENING_LOG_AGGREGATE_CONTACT_DETAILS = false;
	public static final boolean PRE_SCREENING_LOG_SHOW_IC_DATE = false;
	public static final boolean PRE_SCREENING_LOG_SHOW_IC_AGE = false;
	public static final boolean PRE_SCREENING_LOG_SHOW_SCREENING_DATE = false;
	public static final boolean PRE_SCREENING_LOG_SHOW_SCREENING_REASON = false;
	public static final boolean SICL_SHOW_PROBAND_LIST_ENTRY_TAGS = false;
	public static final boolean SICL_SHOW_ALL_PROBAND_LIST_ENTRY_TAGS = false;
	public static final boolean SICL_SHOW_ALL_PROBAND_LIST_ENTRY_TAG_DATES = false;
	public static final boolean SICL_SHOW_INQUIRIES = false;
	public static final boolean SICL_SHOW_ALL_INQUIRIES = false;
	public static final boolean SICL_SHOW_ALL_INQUIRY_DATES = false;
	public static final boolean SICL_SHOW_ADDRESSES = false;
	public static final boolean SICL_SHOW_CONTACT_DETAILS = false;
	public static final boolean SICL_SHOW_TAGS = false;
	public static final boolean SICL_SHOW_ENROLLMENT_STATUS_LOG = false;
	public static final boolean SICL_AGGREGATE_ADDRESSES = false;
	public static final boolean SICL_AGGREGATE_CONTACT_DETAILS = false;
	public static final boolean SICL_SHOW_IC_DATE = false;
	public static final boolean SICL_SHOW_IC_AGE = false;
	public static final boolean SICL_SHOW_SCREENING_DATE = false;
	public static final boolean SICL_SHOW_SCREENING_REASON = false;
	public final static ArrayList<String> PROBAND_LIST_VO_FIELD_COLUMNS = new ArrayList<String>();
	public final static ArrayList<String> ENROLLMENT_LOG_VO_FIELD_COLUMNS = new ArrayList<String>();
	public final static ArrayList<String> SCREENING_LOG_VO_FIELD_COLUMNS = new ArrayList<String>();
	public final static ArrayList<String> PRE_SCREENING_LOG_VO_FIELD_COLUMNS = new ArrayList<String>();
	public final static ArrayList<String> SICL_VO_FIELD_COLUMNS = new ArrayList<String>();
	static
	{
		PROBAND_LIST_VO_FIELD_COLUMNS.add("position");
		PROBAND_LIST_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "department" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "name");
		PROBAND_LIST_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "category" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "name");
		PROBAND_LIST_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "privacyConsentStatus" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR
				+ "name");
		// PROBAND_LIST_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "id",null);
		PROBAND_LIST_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "citizenship");
		PROBAND_LIST_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "firstName");
		PROBAND_LIST_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "lastName");
		PROBAND_LIST_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "animalName");
		PROBAND_LIST_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "nameWithTitles");
		PROBAND_LIST_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "dateOfBirth");
		PROBAND_LIST_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "age");
		PROBAND_LIST_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "gender" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "name");
		PROBAND_LIST_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "comment");
		PROBAND_LIST_VO_FIELD_COLUMNS.add("group" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "title");
		PROBAND_LIST_VO_FIELD_COLUMNS.add("lastStatus" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "status" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "name");
		PROBAND_LIST_VO_FIELD_COLUMNS.add("lastStatus" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "reason");
		PROBAND_LIST_VO_FIELD_COLUMNS.add("lastStatus" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "realTimestamp");
		PROBAND_LIST_VO_FIELD_COLUMNS.add("lastStatus" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "modifiedUser" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR
				+ "identity" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "name");
		// PROBAND_LIST_VO_FIELD_COLUMNS.add("exportStatus",null);
		// PROBAND_LIST_VO_FIELD_COLUMNS.add("exportTimestamp",null);
		// PROBAND_LIST_VO_FIELD_COLUMNS.add("exportResponseMsg",null);
		ENROLLMENT_LOG_VO_FIELD_COLUMNS.add("position");
		ENROLLMENT_LOG_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "firstName");
		ENROLLMENT_LOG_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "lastName");
		ENROLLMENT_LOG_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "dateOfBirth");
		ENROLLMENT_LOG_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "age");
		ENROLLMENT_LOG_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "gender" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "name");
		ENROLLMENT_LOG_VO_FIELD_COLUMNS.add("group" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "title");
		ENROLLMENT_LOG_VO_FIELD_COLUMNS.add("lastStatus" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "status" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "name");
		ENROLLMENT_LOG_VO_FIELD_COLUMNS.add("lastStatus" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "reason");
		ENROLLMENT_LOG_VO_FIELD_COLUMNS.add("lastStatus" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "realTimestamp");
		ENROLLMENT_LOG_VO_FIELD_COLUMNS.add("lastStatus" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "modifiedUser" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR
				+ "identity" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "name");
		SCREENING_LOG_VO_FIELD_COLUMNS.add("position");
		SCREENING_LOG_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "firstName");
		SCREENING_LOG_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "lastName");
		SCREENING_LOG_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "dateOfBirth");
		SCREENING_LOG_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "age");
		SCREENING_LOG_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "gender" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "name");
		SCREENING_LOG_VO_FIELD_COLUMNS.add("group" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "title");
		SCREENING_LOG_VO_FIELD_COLUMNS.add("lastStatus" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "status" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "name");
		SCREENING_LOG_VO_FIELD_COLUMNS.add("lastStatus" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "reason");
		SCREENING_LOG_VO_FIELD_COLUMNS.add("lastStatus" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "realTimestamp");
		SCREENING_LOG_VO_FIELD_COLUMNS.add("lastStatus" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "modifiedUser" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR
				+ "identity" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "name");
		PRE_SCREENING_LOG_VO_FIELD_COLUMNS.add("position");
		PRE_SCREENING_LOG_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "firstName");
		PRE_SCREENING_LOG_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "lastName");
		PRE_SCREENING_LOG_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "dateOfBirth");
		PRE_SCREENING_LOG_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "age");
		PRE_SCREENING_LOG_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "gender" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "name");
		PRE_SCREENING_LOG_VO_FIELD_COLUMNS.add("group" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "title");
		PRE_SCREENING_LOG_VO_FIELD_COLUMNS.add("lastStatus" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "status" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR
				+ "name");
		PRE_SCREENING_LOG_VO_FIELD_COLUMNS.add("lastStatus" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "reason");
		PRE_SCREENING_LOG_VO_FIELD_COLUMNS.add("lastStatus" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "realTimestamp");
		PRE_SCREENING_LOG_VO_FIELD_COLUMNS.add("lastStatus" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "modifiedUser" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR
				+ "identity" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "name");
		SICL_VO_FIELD_COLUMNS.add("position");
		SICL_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "firstName");
		SICL_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "lastName");
		SICL_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "dateOfBirth");
		SICL_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "age");
		SICL_VO_FIELD_COLUMNS.add("proband" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "gender" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "name");
		SICL_VO_FIELD_COLUMNS.add("group" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "title");
		SICL_VO_FIELD_COLUMNS.add("lastStatus" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "status" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "name");
		SICL_VO_FIELD_COLUMNS.add("lastStatus" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "reason");
		SICL_VO_FIELD_COLUMNS.add("lastStatus" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "realTimestamp");
		SICL_VO_FIELD_COLUMNS.add("lastStatus" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "modifiedUser" + ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "identity"
				+ ExcelUtil.COLUMN_NAME_ASSOCIATION_PATH_SEPARATOR + "name");
	}

	private ProbandListExcelDefaultSettings() {
	}
}
