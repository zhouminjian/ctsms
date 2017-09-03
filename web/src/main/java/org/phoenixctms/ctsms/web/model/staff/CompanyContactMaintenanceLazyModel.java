package org.phoenixctms.ctsms.web.model.staff;

import java.util.ArrayList;
import java.util.Collection;

import org.phoenixctms.ctsms.exception.AuthenticationException;
import org.phoenixctms.ctsms.exception.AuthorisationException;
import org.phoenixctms.ctsms.exception.ServiceException;
import org.phoenixctms.ctsms.vo.MaintenanceScheduleItemOutVO;
import org.phoenixctms.ctsms.vo.PSFVO;
import org.phoenixctms.ctsms.web.model.LazyDataModelBase;
import org.phoenixctms.ctsms.web.util.WebUtil;

public class CompanyContactMaintenanceLazyModel extends LazyDataModelBase {

	private Long staffId;

	@Override
	protected Collection<MaintenanceScheduleItemOutVO> getLazyResult(PSFVO psf) {
		if (staffId != null) {
			try {
				return WebUtil.getServiceLocator().getStaffService().getCompanyContactMaintenanceItemList(WebUtil.getAuthentication(), staffId, psf);
			} catch (ServiceException e) {
			} catch (AuthenticationException e) {
				WebUtil.publishException(e);
			} catch (AuthorisationException e) {
			} catch (IllegalArgumentException e) {
			}
		}
		return new ArrayList<MaintenanceScheduleItemOutVO>();
	}

	@Override
	protected MaintenanceScheduleItemOutVO getRowElement(Long id) {
		return WebUtil.getMaintenanceScheduleItem(id);
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}
}
