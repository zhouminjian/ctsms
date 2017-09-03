// Generated by: hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge.

/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package org.phoenixctms.ctsms.domain;

import java.util.Collection;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.phoenixctms.ctsms.enumeration.ECRFValidationStatus;
import org.phoenixctms.ctsms.enumeration.ExportStatus;
import org.phoenixctms.ctsms.query.CriteriaUtil;
import org.phoenixctms.ctsms.query.SubCriteriaMap;
import org.phoenixctms.ctsms.util.L10nUtil;
import org.phoenixctms.ctsms.util.L10nUtil.Locales;
import org.phoenixctms.ctsms.vo.ECRFOutVO;
import org.phoenixctms.ctsms.vo.ECRFStatusEntryVO;
import org.phoenixctms.ctsms.vo.ECRFStatusTypeVO;
import org.phoenixctms.ctsms.vo.PSFVO;
import org.phoenixctms.ctsms.vo.ProbandListEntryOutVO;
import org.phoenixctms.ctsms.vo.UserOutVO;

/**
 * @see ECRFStatusEntry
 */
public class ECRFStatusEntryDaoImpl
extends ECRFStatusEntryDaoBase
{

	private org.hibernate.Criteria createEcrfStatusEntryCriteria() {
		org.hibernate.Criteria ecrfStatusEntryCriteria = this.getSession().createCriteria(ECRFStatusEntry.class);
		return ecrfStatusEntryCriteria;
	}

	public ECRFStatusEntry eCRFStatusEntryVOToEntity(ECRFStatusEntryVO eCRFStatusEntryVO)
	{
		ECRFStatusEntry entity = this.loadECRFStatusEntryFromECRFStatusEntryVO(eCRFStatusEntryVO);
		this.eCRFStatusEntryVOToEntity(eCRFStatusEntryVO, entity, true);
		return entity;
	}

	public void eCRFStatusEntryVOToEntity(
			ECRFStatusEntryVO source,
			ECRFStatusEntry target,
			boolean copyIfNull)
	{
		super.eCRFStatusEntryVOToEntity(source, target, copyIfNull);
		ECRFStatusTypeVO statusVO = source.getStatus();
		ECRFOutVO ecrfVO = source.getEcrf();
		ProbandListEntryOutVO listEntryVO = source.getListEntry();
		UserOutVO modifiedUserVO = source.getModifiedUser();
		if (statusVO != null) {
			target.setStatus(this.getECRFStatusTypeDao().eCRFStatusTypeVOToEntity(statusVO));
		} else if (copyIfNull) {
			target.setStatus(null);
		}
		if (ecrfVO != null) {
			target.setEcrf(this.getECRFDao().eCRFOutVOToEntity(ecrfVO));
		} else if (copyIfNull) {
			target.setEcrf(null);
		}
		if (listEntryVO != null) {
			target.setListEntry(this.getProbandListEntryDao().probandListEntryOutVOToEntity(listEntryVO));
		} else if (copyIfNull) {
			target.setListEntry(null);
		}
		if (modifiedUserVO != null) {
			target.setModifiedUser(this.getUserDao().userOutVOToEntity(modifiedUserVO));
		} else if (copyIfNull) {
			target.setModifiedUser(null);
		}
	}

	@Override
	protected ECRFStatusEntry handleFindByEcrfListEntry(Long ecrfId, Long probandListEntryId) throws Exception {

		org.hibernate.Criteria ecrfStatusEntryCriteria = createEcrfStatusEntryCriteria();
		ecrfStatusEntryCriteria.add(Restrictions.eq("ecrf.id",ecrfId.longValue()));
		ecrfStatusEntryCriteria.add(Restrictions.eq("listEntry.id", probandListEntryId.longValue()));
		ecrfStatusEntryCriteria.setMaxResults(1);
		return (ECRFStatusEntry) ecrfStatusEntryCriteria.uniqueResult();
		// Iterator<ECRFStatusEntry> it = ecrfStatusEntryCriteria.list().iterator();
		// if (it.hasNext()) {
		// return it.next();
		// }
		// return null;

	}

	@Override
	protected Collection<ECRFStatusEntry> handleFindByTrialListEntryEcrfValidationStatusExportStatus(Long trialId, Long probandListEntryId, Long ecrfId,
			ECRFValidationStatus validationStatus, ExportStatus exportStatus, PSFVO psf) throws Exception {





		org.hibernate.Criteria ecrfStatusEntryCriteria = createEcrfStatusEntryCriteria();
		SubCriteriaMap criteriaMap = new SubCriteriaMap(ECRFStatusEntry.class, ecrfStatusEntryCriteria);
		if (trialId != null) {
			org.hibernate.Criteria trialCriteria = ecrfStatusEntryCriteria.createCriteria("listEntry");
			trialCriteria.add(Restrictions.eq("trial.id", trialId.longValue()));
		}
		if (probandListEntryId != null) {
			ecrfStatusEntryCriteria.add(Restrictions.eq("listEntry.id", probandListEntryId.longValue()));
		}
		if (ecrfId != null) {
			ecrfStatusEntryCriteria.add(Restrictions.eq("ecrf.id",ecrfId.longValue()));
		}
		if (validationStatus != null) {
			ecrfStatusEntryCriteria.add(Restrictions.eq("validationStatus",validationStatus));
		}
		if (exportStatus != null) {
			ecrfStatusEntryCriteria.add(Restrictions.eq("exportStatus",exportStatus));
		}
		CriteriaUtil.applyPSFVO(criteriaMap, psf);
		return ecrfStatusEntryCriteria.list();

	}

	@Override
	protected Collection<ECRFStatusEntry> handleFindByTrialListEntryValidatedReviewVerified(Long trialId, Long probandListEntryId,
			Boolean validated, Boolean review, Boolean verified, PSFVO psf) throws Exception {
		org.hibernate.Criteria ecrfStatusEntryCriteria = createEcrfStatusEntryCriteria();
		SubCriteriaMap criteriaMap = new SubCriteriaMap(ECRFStatusEntry.class, ecrfStatusEntryCriteria);
		if (trialId != null) {
			org.hibernate.Criteria trialCriteria = ecrfStatusEntryCriteria.createCriteria("listEntry");
			trialCriteria.add(Restrictions.eq("trial.id", trialId.longValue()));
		}
		if (probandListEntryId != null) {
			ecrfStatusEntryCriteria.add(Restrictions.eq("listEntry.id", probandListEntryId.longValue()));
		}
		if (validated != null || review != null || verified != null) {
			org.hibernate.Criteria statusCriteria = ecrfStatusEntryCriteria.createCriteria("status");
			if (validated != null) {
				statusCriteria.add(Restrictions.eq("validated", validated.booleanValue()));
			}
			if (review != null) {
				statusCriteria.add(Restrictions.eq("review", review.booleanValue()));
			}
			if (verified != null) {
				statusCriteria.add(Restrictions.eq("verified", verified.booleanValue()));
			}
		}
		CriteriaUtil.applyPSFVO(criteriaMap, psf);
		return ecrfStatusEntryCriteria.list();
	}

	@Override
	protected long handleGetCount(Long probandListEntryId, Long ecrfId, Long ecrfStatusTypeId, Boolean valueLockdown, Boolean validated,
			Boolean review, Boolean verified)
					throws Exception {
		// protected long handleGetCount(Long probandListEntryId, Long ecrfId, Long ecrfStatusTypeId, Boolean lockDown) throws Exception {

		org.hibernate.Criteria ecrfStatusEntryCriteria = createEcrfStatusEntryCriteria();
		if (probandListEntryId != null) {
			ecrfStatusEntryCriteria.add(Restrictions.eq("listEntry.id", probandListEntryId.longValue()));
		}
		if (ecrfId != null) {
			ecrfStatusEntryCriteria.add(Restrictions.eq("ecrf.id", ecrfId.longValue()));
		}
		if (ecrfStatusTypeId != null || valueLockdown != null || validated != null || review != null || verified != null) {// || done != null || valueLockdown != null ||
			// fieldStatusLockdown != null) {
			org.hibernate.Criteria ecrfStatusTypeCriteria = ecrfStatusEntryCriteria.createCriteria("status");

			if (ecrfStatusTypeId != null) {
				ecrfStatusTypeCriteria.add(Restrictions.idEq(ecrfStatusTypeId.longValue()));
			}
			if (valueLockdown != null) {
				ecrfStatusTypeCriteria.add(Restrictions.eq("valueLockdown", valueLockdown.booleanValue()));
			}
			if (validated != null) {
				ecrfStatusTypeCriteria.add(Restrictions.eq("validated", validated.booleanValue()));
			}
			if (review != null) {
				ecrfStatusTypeCriteria.add(Restrictions.eq("review", review.booleanValue()));
			}
			if (verified != null) {
				ecrfStatusTypeCriteria.add(Restrictions.eq("verified", verified.booleanValue()));
			}
			// if (done != null) {
			// ecrfStatusTypeCriteria.add(Restrictions.eq("done", done.booleanValue()));
			// }

			// if (valueLockdown != null) {
			// ecrfStatusTypeCriteria.add(Restrictions.eq("fieldStatusLockdown", fieldStatusLockdown.booleanValue()));
			// }
			// if (ecrfStatusTypeId != null) {
			// ecrfStatusTypeCriteria.add(Restrictions.idEq(ecrfStatusTypeId.longValue()));
			// }
			//
			// if (lockDown != null) {
			// ecrfStatusTypeCriteria.add(Restrictions.eq("lockdown", lockDown.booleanValue()));
			// }
		}
		return (Long) ecrfStatusEntryCriteria.setProjection(Projections.rowCount()).uniqueResult();

	}

	/**
	 * Retrieves the entity object that is associated with the specified value object
	 * from the object store. If no such entity object exists in the object store,
	 * a new, blank entity is created
	 */
	private ECRFStatusEntry loadECRFStatusEntryFromECRFStatusEntryVO(ECRFStatusEntryVO eCRFStatusEntryVO)
	{
		// TODO implement loadECRFStatusEntryFromECRFStatusEntryVO
		// throw new UnsupportedOperationException("org.phoenixctms.ctsms.domain.loadECRFStatusEntryFromECRFStatusEntryVO(ECRFStatusEntryVO) not yet implemented.");
		ECRFStatusEntry ecrfStatusEntry = this.load(eCRFStatusEntryVO.getId());
		if (ecrfStatusEntry == null)
		{
			ecrfStatusEntry = ECRFStatusEntry.Factory.newInstance();
		}
		return ecrfStatusEntry;
	}

	public ECRFStatusEntryVO toECRFStatusEntryVO(final ECRFStatusEntry entity)
	{
		return super.toECRFStatusEntryVO(entity);
	}

	public void toECRFStatusEntryVO(
			ECRFStatusEntry source,
			ECRFStatusEntryVO target)
	{
		super.toECRFStatusEntryVO(source, target);
		// WARNING! No conversion for target.status (can't convert source.getStatus():org.phoenixctms.ctsms.domain.ECRFStatusType to org.phoenixctms.ctsms.vo.ECRFStatusTypeVO
		// WARNING! No conversion for target.modifiedUser (can't convert source.getModifiedUser():org.phoenixctms.ctsms.domain.User to org.phoenixctms.ctsms.vo.UserOutVO
		// WARNING! No conversion for target.ecrf (can't convert source.getEcrf():org.phoenixctms.ctsms.domain.ECRF to org.phoenixctms.ctsms.vo.ECRFOutVO
		// WARNING! No conversion for target.listEntry (can't convert source.getListEntry():org.phoenixctms.ctsms.domain.ProbandListEntry to
		// org.phoenixctms.ctsms.vo.ProbandListEntryOutVO
		ECRFStatusType status = source.getStatus();
		ECRF ecrf = source.getEcrf();
		ProbandListEntry listEntry = source.getListEntry();
		User modifiedUser = source.getModifiedUser();
		if (status != null) {
			target.setStatus(this.getECRFStatusTypeDao().toECRFStatusTypeVO(status));
		}
		if (ecrf != null) {
			target.setEcrf(this.getECRFDao().toECRFOutVO(ecrf));
		}
		if (listEntry != null) {
			target.setListEntry(this.getProbandListEntryDao().toProbandListEntryOutVO(listEntry));
		}
		if (modifiedUser != null) {
			target.setModifiedUser(this.getUserDao().toUserOutVO(modifiedUser));
		}
		target.setExportStatus(L10nUtil.createExportStatusVO(Locales.USER, source.getExportStatus()));
		target.setValidationStatus(L10nUtil.createEcrfValidationStatusVO(Locales.USER, source.getValidationStatus()));

	}
}