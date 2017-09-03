// Generated by: hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package org.phoenixctms.ctsms.domain;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.Collection;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.phoenixctms.ctsms.query.CriteriaUtil;
import org.phoenixctms.ctsms.query.SubCriteriaMap;
import org.phoenixctms.ctsms.security.CipherText;
import org.phoenixctms.ctsms.security.CryptoUtil;
import org.phoenixctms.ctsms.util.CoreUtil;
import org.phoenixctms.ctsms.vo.OpsCodeVO;
import org.phoenixctms.ctsms.vo.PSFVO;
import org.phoenixctms.ctsms.vo.ProbandOutVO;
import org.phoenixctms.ctsms.vo.ProcedureInVO;
import org.phoenixctms.ctsms.vo.ProcedureOutVO;
import org.phoenixctms.ctsms.vo.UserOutVO;

/**
 * @see Procedure
 */
public class ProcedureDaoImpl
extends ProcedureDaoBase
{

	private static final String PROCEDURE_NAME = "{0} ({1})";

	private static final String getProcedureName(ProcedureOutVO procedure) {
		if (procedure != null) {
			OpsCodeVO opsCodeVO = procedure.getCode();
			if (opsCodeVO != null) {
				return MessageFormat.format(PROCEDURE_NAME, opsCodeVO.getText(), opsCodeVO.getCode());
			}
		}
		return null;
	}

	private org.hibernate.Criteria createProcedureCriteria() {
		org.hibernate.Criteria procedureCriteria = this.getSession().createCriteria(Procedure.class);
		return procedureCriteria;
	}

	/**
	 * @throws Exception
	 * @inheritDoc
	 */
	@Override
	protected Collection<Procedure> handleFindByProband(Long probandId, PSFVO psf) throws Exception
	{
		org.hibernate.Criteria procedureCriteria = createProcedureCriteria();
		SubCriteriaMap criteriaMap = new SubCriteriaMap(Procedure.class, procedureCriteria);
		if (probandId != null) {
			procedureCriteria.add(Restrictions.eq("proband.id", probandId.longValue()));
		}
		CriteriaUtil.applyPSFVO(criteriaMap, psf);
		return procedureCriteria.list();
	}

	/**
	 * @inheritDoc
	 */
	@Override
	protected Collection<Procedure> handleFindCollidingProbandCodeInterval(Long probandId, Long codeId, Timestamp from, Timestamp to)
	{
		org.hibernate.Criteria procedureCriteria = createProcedureCriteria();
		if (from != null) {
			procedureCriteria.add(Restrictions.isNotNull("start"));
			CriteriaUtil.applyStopOptionalIntervalCriterion(procedureCriteria, from, to, null, true);
		} else { // saved records without start stop
			procedureCriteria.add(Restrictions.isNull("start"));
		}
		// if (probandId != null) {
		procedureCriteria.add(Restrictions.eq("proband.id", probandId.longValue()));
		// }
		if (codeId != null) {
			procedureCriteria.add(Restrictions.eq("code.id", codeId.longValue()));
		}
		return procedureCriteria.list();
	}

	@Override
	protected long handleGetCount(Long probandId) throws Exception
	{
		org.hibernate.Criteria procedureCriteria = createProcedureCriteria();
		if (probandId != null) {
			procedureCriteria.add(Restrictions.eq("proband.id", probandId.longValue()));
		}
		return (Long) procedureCriteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	/**
	 * Retrieves the entity object that is associated with the specified value object
	 * from the object store. If no such entity object exists in the object store,
	 * a new, blank entity is created
	 */
	private Procedure loadProcedureFromProcedureInVO(ProcedureInVO procedureInVO)
	{
		// TODO implement loadProcedureFromProcedureInVO
		// throw new UnsupportedOperationException("org.phoenixctms.ctsms.domain.loadProcedureFromProcedureInVO(ProcedureInVO) not yet implemented.");
		Long id = procedureInVO.getId();
		Procedure procedure = null;
		if (id != null) {
			procedure = this.load(id);
		}
		if (procedure == null)
		{
			procedure = Procedure.Factory.newInstance();
		}
		return procedure;
	}

	/**
	 * Retrieves the entity object that is associated with the specified value object
	 * from the object store. If no such entity object exists in the object store,
	 * a new, blank entity is created
	 */
	private Procedure loadProcedureFromProcedureOutVO(ProcedureOutVO procedureOutVO)
	{
		// TODO implement loadProcedureFromProcedureOutVO
		// throw new UnsupportedOperationException("org.phoenixctms.ctsms.domain.loadProcedureFromProcedureOutVO(ProcedureOutVO) not yet implemented.");
		Procedure procedure = this.load(procedureOutVO.getId());
		if (procedure == null)
		{
			procedure = Procedure.Factory.newInstance();
		}
		return procedure;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Procedure procedureInVOToEntity(ProcedureInVO procedureInVO)
	{
		Procedure entity = this.loadProcedureFromProcedureInVO(procedureInVO);
		this.procedureInVOToEntity(procedureInVO, entity, true);
		return entity;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void procedureInVOToEntity(
			ProcedureInVO source,
			Procedure target,
			boolean copyIfNull)
	{
		super.procedureInVOToEntity(source, target, copyIfNull);
		Long codeId = source.getCodeId();
		Long probandId = source.getProbandId();
		// if (codeId != null) {
		// target.setCode(this.getOpsCodeDao().load(codeId));
		// } else if (copyIfNull) {
		// target.setCode(null);
		// }
		if (codeId != null) {
			OpsCode opsCode = this.getOpsCodeDao().load(codeId);
			target.setCode(opsCode);
			opsCode.addProcedures(target);
		} else if (copyIfNull) {
			OpsCode opsCode = target.getCode();
			target.setCode(null);
			if (opsCode != null) {
				opsCode.removeProcedures(target);
			}
		}
		if (probandId != null) {
			Proband proband = this.getProbandDao().load(probandId);
			target.setProband(proband);
			proband.addProcedures(target);
		} else if (copyIfNull) {
			Proband proband = target.getProband();
			target.setProband(null);
			if (proband != null) {
				proband.removeProcedures(target);
			}
		}
		try {
			if (copyIfNull || source.getComment() != null) {
				CipherText cipherText = CryptoUtil.encryptValue(source.getComment());
				target.setCommentIv(cipherText.getIv());
				target.setEncryptedComment(cipherText.getCipherText());
				target.setCommentHash(CryptoUtil.hashForSearch(source.getComment()));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Procedure procedureOutVOToEntity(ProcedureOutVO procedureOutVO)
	{
		Procedure entity = this.loadProcedureFromProcedureOutVO(procedureOutVO);
		this.procedureOutVOToEntity(procedureOutVO, entity, true);
		return entity;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void procedureOutVOToEntity(
			ProcedureOutVO source,
			Procedure target,
			boolean copyIfNull)
	{
		super.procedureOutVOToEntity(source, target, copyIfNull);
		OpsCodeVO codeVO = source.getCode();
		ProbandOutVO probandVO = source.getProband();
		UserOutVO modifiedUserVO = source.getModifiedUser();
		// if (codeVO != null) {
		// target.setCode(this.getOpsCodeDao().opsCodeVOToEntity(codeVO));
		// } else if (copyIfNull) {
		// target.setCode(null);
		// }
		if (codeVO != null) {
			OpsCode opsCode = this.getOpsCodeDao().opsCodeVOToEntity(codeVO);
			target.setCode(opsCode);
			opsCode.addProcedures(target);
		} else if (copyIfNull) {
			OpsCode opsCode = target.getCode();
			target.setCode(null);
			if (opsCode != null) {
				opsCode.removeProcedures(target);
			}
		}
		if (probandVO != null) {
			Proband proband = this.getProbandDao().probandOutVOToEntity(probandVO);
			target.setProband(proband);
			proband.addProcedures(target);
		} else if (copyIfNull) {
			Proband proband = target.getProband();
			target.setProband(null);
			if (proband != null) {
				proband.removeProcedures(target);
			}
		}
		if (modifiedUserVO != null) {
			target.setModifiedUser(this.getUserDao().userOutVOToEntity(modifiedUserVO));
		} else if (copyIfNull) {
			target.setModifiedUser(null);
		}
		try {
			if (copyIfNull || source.getComment() != null) {
				CipherText cipherText = CryptoUtil.encryptValue(source.getComment());
				target.setCommentIv(cipherText.getIv());
				target.setEncryptedComment(cipherText.getCipherText());
				target.setCommentHash(CryptoUtil.hashForSearch(source.getComment()));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public ProcedureInVO toProcedureInVO(final Procedure entity)
	{
		return super.toProcedureInVO(entity);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void toProcedureInVO(
			Procedure source,
			ProcedureInVO target)
	{
		super.toProcedureInVO(source, target);
		OpsCode code = source.getCode();
		Proband proband = source.getProband();
		if (code != null) {
			target.setCodeId(code.getId());
		}
		if (proband != null) {
			target.setProbandId(proband.getId());
		}
		try {
			target.setComment((String) CryptoUtil.decryptValue(source.getCommentIv(), source.getEncryptedComment()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public ProcedureOutVO toProcedureOutVO(final Procedure entity)
	{
		return super.toProcedureOutVO(entity);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void toProcedureOutVO(
			Procedure source,
			ProcedureOutVO target)
	{
		super.toProcedureOutVO(source, target);
		// WARNING! No conversion for target.proband (can't convert source.getProband():org.phoenixctms.ctsms.domain.Proband to org.phoenixctms.ctsms.vo.ProbandOutVO
		// WARNING! No conversion for target.modifiedUser (can't convert source.getModifiedUser():org.phoenixctms.ctsms.domain.User to org.phoenixctms.ctsms.vo.UserOutVO
		// WARNING! No conversion for target.code (can't convert source.getCode():org.phoenixctms.ctsms.domain.OpsCode to org.phoenixctms.ctsms.vo.OpsCodeVO
		OpsCode code = source.getCode();
		Proband proband = source.getProband();
		User modifiedUser = source.getModifiedUser();
		if (code != null) {
			target.setCode(this.getOpsCodeDao().toOpsCodeVO(code));
		}
		if (proband != null) {
			target.setProband(this.getProbandDao().toProbandOutVO(proband));
		}
		if (modifiedUser != null) {
			target.setModifiedUser(this.getUserDao().toUserOutVO(modifiedUser));
		}
		try {
			if (!CoreUtil.isPassDecryption()) {
				throw new Exception();
			}
			target.setComment((String) CryptoUtil.decryptValue(source.getCommentIv(), source.getEncryptedComment()));
			target.setDecrypted(true);
		} catch (Exception e) {
			target.setComment(null);
			target.setDecrypted(false);
		}
		target.setName(getProcedureName(target));
	}
}