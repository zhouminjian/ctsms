// Generated by: hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package org.phoenixctms.ctsms.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.phoenixctms.ctsms.compare.FieldComparator;
import org.phoenixctms.ctsms.query.CategoryCriterion;
import org.phoenixctms.ctsms.query.CriteriaUtil;
import org.phoenixctms.ctsms.util.DefaultSettings;
import org.phoenixctms.ctsms.util.SettingCodes;
import org.phoenixctms.ctsms.util.Settings;
import org.phoenixctms.ctsms.util.Settings.Bundle;
import org.phoenixctms.ctsms.vo.OpsSystCategoryVO;
import org.phoenixctms.ctsms.vo.OpsSystModifierVO;

/**
 * @see OpsSystCategory
 */
public class OpsSystCategoryDaoImpl
extends OpsSystCategoryDaoBase
{

	@Override
	protected Collection<String> handleFindCategoryPreferredRubricLabels(
			String preferredRubricLabelInfix, Integer limit) throws Exception {
		org.hibernate.Criteria opsSystCategoryCriteria = this.getSession().createCriteria(OpsSystCategory.class);
		opsSystCategoryCriteria.setCacheable(true);
		CategoryCriterion.apply(opsSystCategoryCriteria, new CategoryCriterion(preferredRubricLabelInfix, "preferredRubricLabel", MatchMode.ANYWHERE));
		opsSystCategoryCriteria.add(Restrictions.not(Restrictions.or(Restrictions.eq("preferredRubricLabel", ""), Restrictions.isNull("preferredRubricLabel"))));
		// opsSystCategoryCriteria.add(Restrictions.eq("revision",
		// Settings.getString(SettingCodes.OPS_SYSTEMATICS_REVISION, Bundle.SETTINGS, DefaultSettings.OPS_SYSTEMATICS_REVISION)));
		opsSystCategoryCriteria.addOrder(Order.asc("preferredRubricLabel"));
		opsSystCategoryCriteria.setProjection(Projections.distinct(Projections.property("preferredRubricLabel")));
		CriteriaUtil.applyLimit(limit, Settings.getIntNullable(SettingCodes.OPS_SYST_CATEGORY_PREFERRED_RUBRIC_LABEL_AUTOCOMPLETE_DEFAULT_RESULT_LIMIT, Bundle.SETTINGS,
				DefaultSettings.OPS_SYST_CATEGORY_PREFERRED_RUBRIC_LABEL_AUTOCOMPLETE_DEFAULT_RESULT_LIMIT), opsSystCategoryCriteria);
		return opsSystCategoryCriteria.list();
	}

	/**
	 * Retrieves the entity object that is associated with the specified value object
	 * from the object store. If no such entity object exists in the object store,
	 * a new, blank entity is created
	 */
	private OpsSystCategory loadOpsSystCategoryFromOpsSystCategoryVO(OpsSystCategoryVO opsSystCategoryVO)
	{
		// TODO implement loadOpsSystCategoryFromOpsSystCategoryVO
		// throw new UnsupportedOperationException("org.phoenixctms.ctsms.domain.loadOpsSystCategoryFromOpsSystCategoryVO(OpsSystCategoryVO) not yet implemented.");
		Long id = opsSystCategoryVO.getId();
		OpsSystCategory opsSystCategory = null;
		if (id != null) {
			opsSystCategory = this.load(id);
		}
		if (opsSystCategory == null)
		{
			opsSystCategory = OpsSystCategory.Factory.newInstance();
		}
		return opsSystCategory;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public OpsSystCategory opsSystCategoryVOToEntity(OpsSystCategoryVO opsSystCategoryVO)
	{
		OpsSystCategory entity = this.loadOpsSystCategoryFromOpsSystCategoryVO(opsSystCategoryVO);
		this.opsSystCategoryVOToEntity(opsSystCategoryVO, entity, true);
		return entity;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void opsSystCategoryVOToEntity(
			OpsSystCategoryVO source,
			OpsSystCategory target,
			boolean copyIfNull)
	{
		super.opsSystCategoryVOToEntity(source, target, copyIfNull);
		Collection modifiers = source.getModifiers();
		if (modifiers.size() > 0) {
			this.getOpsSystModifierDao().opsSystModifierVOToEntityCollection(modifiers); // wont work?
			target.setModifiers(modifiers);
		} else if (copyIfNull) {
			target.getModifiers().clear();
		}
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public OpsSystCategoryVO toOpsSystCategoryVO(final OpsSystCategory entity)
	{
		return super.toOpsSystCategoryVO(entity);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void toOpsSystCategoryVO(
			OpsSystCategory source,
			OpsSystCategoryVO target)
	{
		super.toOpsSystCategoryVO(source, target);
		target.setModifiers(toOpsSystModifierVOCollection(source.getModifiers()));
	}

	private ArrayList<OpsSystModifierVO> toOpsSystModifierVOCollection(Collection<OpsSystModifier> modifiers) {
		// related to http://forum.andromda.org/viewtopic.php?t=4288
		OpsSystModifierDao opsSystModifierDao = this.getOpsSystModifierDao();
		ArrayList<OpsSystModifierVO> result = new ArrayList<OpsSystModifierVO>(modifiers.size());
		Iterator<OpsSystModifier> it = modifiers.iterator();
		while (it.hasNext()) {
			result.add(opsSystModifierDao.toOpsSystModifierVO(it.next()));
		}
		Collections.sort(result, new FieldComparator(false, "getLevel"));
		return result;
	}
}