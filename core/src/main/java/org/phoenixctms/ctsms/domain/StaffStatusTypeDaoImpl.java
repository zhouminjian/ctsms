// Generated by: hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package org.phoenixctms.ctsms.domain;

import java.util.Collection;

import org.phoenixctms.ctsms.query.CriteriaUtil;
import org.phoenixctms.ctsms.util.L10nUtil;
import org.phoenixctms.ctsms.util.L10nUtil.Locales;
import org.phoenixctms.ctsms.vo.StaffStatusTypeVO;

/**
 * @see StaffStatusType
 */
public class StaffStatusTypeDaoImpl
		extends StaffStatusTypeDaoBase
{

	@Override
	protected Collection<StaffStatusType> handleFindByVisibleId(Boolean visible, Long typeId)
			throws Exception {
		org.hibernate.Criteria statusTypeCriteria = this.getSession().createCriteria(StaffStatusType.class);
		statusTypeCriteria.setCacheable(true);
		CriteriaUtil.applyVisibleIdCriterion("visible", statusTypeCriteria, visible, typeId);
		return statusTypeCriteria.list();
	}

	/**
	 * Retrieves the entity object that is associated with the specified value object
	 * from the object store. If no such entity object exists in the object store,
	 * a new, blank entity is created
	 */
	private StaffStatusType loadStaffStatusTypeFromStaffStatusTypeVO(StaffStatusTypeVO staffStatusTypeVO)
	{
		StaffStatusType staffStatusType = null;
		Long id = staffStatusTypeVO.getId();
		if (id != null) {
			staffStatusType = this.load(id);
		}
		if (staffStatusType == null)
		{
			staffStatusType = StaffStatusType.Factory.newInstance();
		}
		return staffStatusType;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public StaffStatusType staffStatusTypeVOToEntity(StaffStatusTypeVO staffStatusTypeVO)
	{
		StaffStatusType entity = this.loadStaffStatusTypeFromStaffStatusTypeVO(staffStatusTypeVO);
		this.staffStatusTypeVOToEntity(staffStatusTypeVO, entity, true);
		return entity;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void staffStatusTypeVOToEntity(
			StaffStatusTypeVO source,
			StaffStatusType target,
			boolean copyIfNull)
	{
		super.staffStatusTypeVOToEntity(source, target, copyIfNull);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public StaffStatusTypeVO toStaffStatusTypeVO(final StaffStatusType entity)
	{
		return super.toStaffStatusTypeVO(entity);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void toStaffStatusTypeVO(
			StaffStatusType source,
			StaffStatusTypeVO target)
	{
		super.toStaffStatusTypeVO(source, target);
		target.setName(L10nUtil.getStaffStatusTypeName(Locales.USER, source.getNameL10nKey()));
	}
}