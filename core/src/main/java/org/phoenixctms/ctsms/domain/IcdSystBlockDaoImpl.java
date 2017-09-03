// Generated by: hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package org.phoenixctms.ctsms.domain;

import java.util.Collection;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.phoenixctms.ctsms.query.CategoryCriterion;
import org.phoenixctms.ctsms.query.CriteriaUtil;
import org.phoenixctms.ctsms.util.DefaultSettings;
import org.phoenixctms.ctsms.util.SettingCodes;
import org.phoenixctms.ctsms.util.Settings;
import org.phoenixctms.ctsms.util.Settings.Bundle;
import org.phoenixctms.ctsms.vo.IcdSystBlockVO;

/**
 * @see IcdSystBlock
 */
public class IcdSystBlockDaoImpl
extends IcdSystBlockDaoBase
{

	@Override
	protected Collection<String> handleFindBlockPreferredRubricLabels(
			String preferredRubricLabelInfix, Integer limit) throws Exception {
		org.hibernate.Criteria icdSystBlockCriteria = this.getSession().createCriteria(IcdSystBlock.class);
		icdSystBlockCriteria.setCacheable(true);
		CategoryCriterion.apply(icdSystBlockCriteria, new CategoryCriterion(preferredRubricLabelInfix, "preferredRubricLabel", MatchMode.ANYWHERE));
		icdSystBlockCriteria.add(Restrictions.not(Restrictions.or(Restrictions.eq("preferredRubricLabel", ""), Restrictions.isNull("preferredRubricLabel"))));
		// icdSystBlockCriteria.add(Restrictions.eq("revision", Settings.getString(SettingCodes.ICD_SYSTEMATICS_REVISION, Bundle.SETTINGS,
		// DefaultSettings.ICD_SYSTEMATICS_REVISION)));
		icdSystBlockCriteria.addOrder(Order.asc("preferredRubricLabel"));
		icdSystBlockCriteria.setProjection(Projections.distinct(Projections.property("preferredRubricLabel")));
		CriteriaUtil.applyLimit(limit, Settings.getIntNullable(SettingCodes.ICD_SYST_BLOCK_PREFERRED_RUBRIC_LABEL_AUTOCOMPLETE_DEFAULT_RESULT_LIMIT, Bundle.SETTINGS,
				DefaultSettings.ICD_SYST_BLOCK_PREFERRED_RUBRIC_LABEL_AUTOCOMPLETE_DEFAULT_RESULT_LIMIT), icdSystBlockCriteria);
		return icdSystBlockCriteria.list();
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public IcdSystBlock icdSystBlockVOToEntity(IcdSystBlockVO icdSystBlockVO)
	{
		IcdSystBlock entity = this.loadIcdSystBlockFromIcdSystBlockVO(icdSystBlockVO);
		this.icdSystBlockVOToEntity(icdSystBlockVO, entity, true);
		return entity;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void icdSystBlockVOToEntity(
			IcdSystBlockVO source,
			IcdSystBlock target,
			boolean copyIfNull)
	{
		super.icdSystBlockVOToEntity(source, target, copyIfNull);
	}

	/**
	 * Retrieves the entity object that is associated with the specified value object
	 * from the object store. If no such entity object exists in the object store,
	 * a new, blank entity is created
	 */
	private IcdSystBlock loadIcdSystBlockFromIcdSystBlockVO(IcdSystBlockVO icdSystBlockVO)
	{
		// TODO implement loadIcdSystBlockFromIcdSystBlockVO
		// throw new UnsupportedOperationException("org.phoenixctms.ctsms.domain.loadIcdSystBlockFromIcdSystBlockVO(IcdSystBlockVO) not yet implemented.");
		Long id = icdSystBlockVO.getId();
		IcdSystBlock icdSystBlock = null;
		if (id != null) {
			icdSystBlock = this.load(id);
		}
		if (icdSystBlock == null)
		{
			icdSystBlock = IcdSystBlock.Factory.newInstance();
		}
		return icdSystBlock;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public IcdSystBlockVO toIcdSystBlockVO(final IcdSystBlock entity)
	{
		return super.toIcdSystBlockVO(entity);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void toIcdSystBlockVO(
			IcdSystBlock source,
			IcdSystBlockVO target)
	{
		super.toIcdSystBlockVO(source, target);
	}
}