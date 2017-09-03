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
import org.phoenixctms.ctsms.vo.InventoryCategoryVO;

/**
 * @see InventoryCategory
 */
public class InventoryCategoryDaoImpl
		extends InventoryCategoryDaoBase
{

	@Override
	protected Collection<InventoryCategory> handleFindByVisibleId(Boolean visible, Long categoryId)
			throws Exception {
		org.hibernate.Criteria categoryCriteria = this.getSession().createCriteria(InventoryCategory.class);
		categoryCriteria.setCacheable(true);
		CriteriaUtil.applyVisibleIdCriterion("visible", categoryCriteria, visible, categoryId);
		return categoryCriteria.list();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InventoryCategory inventoryCategoryVOToEntity(InventoryCategoryVO inventoryCategoryVO)
	{
		InventoryCategory entity = this.loadInventoryCategoryFromInventoryCategoryVO(inventoryCategoryVO);
		this.inventoryCategoryVOToEntity(inventoryCategoryVO, entity, true);
		return entity;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void inventoryCategoryVOToEntity(
			InventoryCategoryVO source,
			InventoryCategory target,
			boolean copyIfNull)
	{
		super.inventoryCategoryVOToEntity(source, target, copyIfNull);
	}

	/**
	 * Retrieves the entity object that is associated with the specified value object
	 * from the object store. If no such entity object exists in the object store,
	 * a new, blank entity is created
	 */
	private InventoryCategory loadInventoryCategoryFromInventoryCategoryVO(InventoryCategoryVO inventoryCategoryVO)
	{
		InventoryCategory inventoryCategory = null;
		Long id = inventoryCategoryVO.getId();
		if (id != null) {
			inventoryCategory = this.load(id);
		}
		if (inventoryCategory == null)
		{
			inventoryCategory = InventoryCategory.Factory.newInstance();
		}
		return inventoryCategory;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InventoryCategoryVO toInventoryCategoryVO(final InventoryCategory entity)
	{
		return super.toInventoryCategoryVO(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void toInventoryCategoryVO(
			InventoryCategory source,
			InventoryCategoryVO target)
	{
		super.toInventoryCategoryVO(source, target);
		target.setName(L10nUtil.getInventoryCategoryName(Locales.USER, source.getNameL10nKey()));
	}
}