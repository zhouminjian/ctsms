// Generated by: hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package org.phoenixctms.ctsms.domain;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.phoenixctms.ctsms.query.CategoryCriterion;
import org.phoenixctms.ctsms.query.CriteriaUtil;
import org.phoenixctms.ctsms.util.CommonUtil;
import org.phoenixctms.ctsms.util.DefaultSettings;
import org.phoenixctms.ctsms.util.SettingCodes;
import org.phoenixctms.ctsms.util.Settings;
import org.phoenixctms.ctsms.util.Settings.Bundle;
import org.phoenixctms.ctsms.vo.AlphaIdVO;
import org.phoenixctms.ctsms.vo.IcdSystVO;

/**
 * @see AlphaId
 */
public class AlphaIdDaoImpl
extends AlphaIdDaoBase
{

	private final static boolean MATCH_PRIMARY_CODE = true;

	private static void applyAlphaIdCodeCriterions(org.hibernate.Criteria alphaIdCriteria, String codePrefix) {
		CategoryCriterion.apply(alphaIdCriteria, new CategoryCriterion(codePrefix, "primaryCode", MatchMode.START));
		applyAlphaIdCriterions(alphaIdCriteria);
	}

	private static void applyAlphaIdCriterions(org.hibernate.Criteria alphaIdCriteria) {
		alphaIdCriteria.add(Restrictions.eq("revision", Settings.getString(SettingCodes.ALPHA_ID_REVISION, Bundle.SETTINGS, DefaultSettings.ALPHA_ID_REVISION)));
		alphaIdCriteria.add(Restrictions.and(Restrictions.ne("primaryCode", ""), Restrictions.isNotNull("primaryCode")));
		alphaIdCriteria.add(Restrictions.eq("valid", true));
	}

	private static void applyAlphaIdTextCriterions(org.hibernate.Criteria alphaIdCriteria, String textInfix) {
		ArrayList<CategoryCriterion> criterions = new ArrayList<CategoryCriterion>();
		criterions.add(new CategoryCriterion(textInfix, "text", MatchMode.ANYWHERE));
		if (MATCH_PRIMARY_CODE) {
			criterions.add(new CategoryCriterion(textInfix, "primaryCode", MatchMode.EXACT));
		}
		CategoryCriterion.applyOr(alphaIdCriteria, criterions);
		applyAlphaIdCriterions(alphaIdCriteria);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public AlphaId alphaIdVOToEntity(AlphaIdVO alphaIdVO)
	{
		AlphaId entity = this.loadAlphaIdFromAlphaIdVO(alphaIdVO);
		this.alphaIdVOToEntity(alphaIdVO, entity, true);
		return entity;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void alphaIdVOToEntity(
			AlphaIdVO source,
			AlphaId target,
			boolean copyIfNull)
	{
		super.alphaIdVOToEntity(source, target, copyIfNull);
		IcdSystVO systematicsVO = source.getSystematics();
		// if (systematicsVO != null) {
		// target.setSystematics(this.getIcdSystDao().icdSystVOToEntity(systematicsVO));
		// } else if (copyIfNull) {
		// target.setSystematics(null);
		// }
		if (systematicsVO != null) {
			IcdSyst systematics = this.getIcdSystDao().icdSystVOToEntity(systematicsVO);
			target.setSystematics(systematics);
			systematics.addAlphaIds(target);
		} else if (copyIfNull) {
			IcdSyst systematics = target.getSystematics();
			target.setSystematics(null);
			if (systematics != null) {
				systematics.removeAlphaIds(target);
			}
		}
	}

	private org.hibernate.Criteria createAlphaIdCriteria(boolean cacheable) {
		org.hibernate.Criteria alphaIdCriteria = this.getSession().createCriteria(AlphaId.class);
		if (cacheable) {
			alphaIdCriteria.setCacheable(true);
		}
		return alphaIdCriteria;
	}

	@Override
	protected Collection<String> handleFindAlphaIdCodes(String codePrefix,
			Integer limit) throws Exception {
		org.hibernate.Criteria alphaIdCriteria = createAlphaIdCriteria(true);
		applyAlphaIdCodeCriterions(alphaIdCriteria, codePrefix);
		alphaIdCriteria.add(Restrictions.not(Restrictions.or(Restrictions.eq("primaryCode", ""), Restrictions.isNull("primaryCode"))));
		alphaIdCriteria.addOrder(Order.asc("primaryCode"));
		alphaIdCriteria.setProjection(Projections.distinct(Projections.property("primaryCode")));
		CriteriaUtil.applyLimit(limit, Settings.getIntNullable(SettingCodes.ALPHA_ID_CODE_AUTOCOMPLETE_DEFAULT_RESULT_LIMIT, Bundle.SETTINGS,
				DefaultSettings.ALPHA_ID_CODE_AUTOCOMPLETE_DEFAULT_RESULT_LIMIT), alphaIdCriteria);
		return alphaIdCriteria.list();
	}

	@Override
	protected Collection<AlphaId> handleFindAlphaIds(String textInfix, Integer limit)
	{
		org.hibernate.Criteria alphaIdCriteria = createAlphaIdCriteria(true);
		applyAlphaIdTextCriterions(alphaIdCriteria, textInfix);
		alphaIdCriteria.addOrder(Order.asc("text"));
		CriteriaUtil.applyLimit(limit,
				Settings.getIntNullable(SettingCodes.ALPHA_ID_AUTOCOMPLETE_DEFAULT_RESULT_LIMIT, Bundle.SETTINGS, DefaultSettings.ALPHA_ID_AUTOCOMPLETE_DEFAULT_RESULT_LIMIT),
				alphaIdCriteria);
		return alphaIdCriteria.list();
	}

	@Override
	protected Collection<String> handleFindAlphaIdTexts(String textInfix, Integer limit) {
		org.hibernate.Criteria alphaIdCriteria = createAlphaIdCriteria(true);
		applyAlphaIdTextCriterions(alphaIdCriteria, textInfix);
		alphaIdCriteria.add(Restrictions.not(Restrictions.or(Restrictions.eq("text", ""), Restrictions.isNull("text"))));
		alphaIdCriteria.addOrder(Order.asc("text"));
		alphaIdCriteria.setProjection(Projections.distinct(Projections.property("text")));
		CriteriaUtil.applyLimit(limit, Settings.getIntNullable(SettingCodes.ALPHA_ID_TEXT_AUTOCOMPLETE_DEFAULT_RESULT_LIMIT, Bundle.SETTINGS,
				DefaultSettings.ALPHA_ID_TEXT_AUTOCOMPLETE_DEFAULT_RESULT_LIMIT), alphaIdCriteria);
		return alphaIdCriteria.list();
	}

	@Override
	protected long handleGetDiagnosisCount(String revision) throws Exception
	{
		org.hibernate.Criteria alphaIdCriteria =createAlphaIdCriteria(false);
		alphaIdCriteria.add(Restrictions.eq("revision", revision));
		alphaIdCriteria.createCriteria("diagnoses",CriteriaSpecification.INNER_JOIN);
		return (Long) alphaIdCriteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	/**
	 * Retrieves the entity object that is associated with the specified value object
	 * from the object store. If no such entity object exists in the object store,
	 * a new, blank entity is created
	 */
	private AlphaId loadAlphaIdFromAlphaIdVO(AlphaIdVO alphaIdVO)
	{
		// TODO implement loadAlphaIdFromAlphaIdVO
		// throw new UnsupportedOperationException("org.phoenixctms.ctsms.domain.loadAlphaIdFromAlphaIdVO(AlphaIdVO) not yet implemented.");
		Long id = alphaIdVO.getId();
		AlphaId alphaId = null;
		if (id != null) {
			alphaId = this.load(id);
		}
		if (alphaId == null)
		{
			alphaId = AlphaId.Factory.newInstance();
		}
		return alphaId;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public AlphaIdVO toAlphaIdVO(final AlphaId entity)
	{
		return super.toAlphaIdVO(entity);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void toAlphaIdVO(
			AlphaId source,
			AlphaIdVO target)
	{
		super.toAlphaIdVO(source, target);
		IcdSyst systematics = source.getSystematics();
		if (systematics != null) {
			target.setSystematics(this.getIcdSystDao().toIcdSystVO(systematics));
		}
		StringBuilder code = new StringBuilder();
		if (!CommonUtil.isEmptyString(source.getPrimaryCode())) {
			code.append(source.getPrimaryCode());
		}
		if (!CommonUtil.isEmptyString(source.getAsteriskCode())) {
			if (code.length() > 0) {
				code.append(" ");
			}
			code.append(source.getAsteriskCode());
		}
		if (!CommonUtil.isEmptyString(source.getOptionalCode())) {
			if (code.length() > 0) {
				code.append(" ");
			}
			code.append(source.getOptionalCode());
		}
		target.setCode(code.toString());
	}
}