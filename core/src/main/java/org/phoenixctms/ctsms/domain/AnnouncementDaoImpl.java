// Generated by: hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package org.phoenixctms.ctsms.domain;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import org.phoenixctms.ctsms.vo.AnnouncementVO;

/**
 * @see Announcement
 */
public class AnnouncementDaoImpl
		extends AnnouncementDaoBase
{

	/**
	 * @inheritDoc
	 */
	@Override
	public Announcement announcementVOToEntity(AnnouncementVO announcementVO)
	{
		// TODO verify behavior of announcementVOToEntity
		Announcement entity = this.loadAnnouncementFromAnnouncementVO(announcementVO);
		this.announcementVOToEntity(announcementVO, entity, true);
		return entity;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void announcementVOToEntity(
			AnnouncementVO source,
			Announcement target,
			boolean copyIfNull)
	{
		// TODO verify behavior of announcementVOToEntity
		super.announcementVOToEntity(source, target, copyIfNull);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	protected Announcement handleGetAnnouncement()
	{
		org.hibernate.Criteria announcementCriteria = this.getSession().createCriteria(Announcement.class);
		announcementCriteria.setCacheable(true);
		announcementCriteria.add(Restrictions.eq("visible", true));
		announcementCriteria.addOrder(Order.desc("timestamp"));
		announcementCriteria.addOrder(Order.desc("id"));
		announcementCriteria.setMaxResults(1);
		return (Announcement) announcementCriteria.uniqueResult();
	}

	/**
	 * Retrieves the entity object that is associated with the specified value object
	 * from the object store. If no such entity object exists in the object store,
	 * a new, blank entity is created
	 */
	private Announcement loadAnnouncementFromAnnouncementVO(AnnouncementVO announcementVO)
	{
		// TODO implement loadAnnouncementFromAnnouncementVO
		// throw new UnsupportedOperationException("org.phoenixctms.ctsms.domain.loadAnnouncementFromAnnouncementVO(AnnouncementVO) not yet implemented.");
		// A typical implementation looks like this:
		Long id = announcementVO.getId();
		Announcement announcement = null;
		if (id != null) {
			announcement = this.load(id);
		}
		if (announcement == null)
		{
			announcement = Announcement.Factory.newInstance();
		}
		return announcement;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public AnnouncementVO toAnnouncementVO(final Announcement entity)
	{
		// TODO verify behavior of toAnnouncementVO
		return super.toAnnouncementVO(entity);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void toAnnouncementVO(
			Announcement source,
			AnnouncementVO target)
	{
		// TODO verify behavior of toAnnouncementVO
		super.toAnnouncementVO(source, target);
	}
}