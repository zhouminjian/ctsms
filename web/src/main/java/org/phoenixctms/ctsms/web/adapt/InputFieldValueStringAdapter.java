package org.phoenixctms.ctsms.web.adapt;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.phoenixctms.ctsms.adapt.InputFieldValueStringAdapterBase;
import org.phoenixctms.ctsms.vo.InputFieldSelectionSetValueOutVO;
import org.phoenixctms.ctsms.web.util.DateUtil;
import org.phoenixctms.ctsms.web.util.MessageCodes;
import org.phoenixctms.ctsms.web.util.Messages;
import org.phoenixctms.ctsms.web.util.WebUtil;

public abstract class InputFieldValueStringAdapter<VALUEVO> extends InputFieldValueStringAdapterBase<VALUEVO> {

	private final static String SELECTION_SET_VALUES_SEPARATOR = ", ";

	protected static Collection<InputFieldSelectionSetValueOutVO> getSelectionSetValuesFromIds(Collection<Long> selectionValueIds) {
		ArrayList<InputFieldSelectionSetValueOutVO> result;
		if (selectionValueIds != null && selectionValueIds.size() > 0) {
			result = new ArrayList<InputFieldSelectionSetValueOutVO>(selectionValueIds.size());
			Iterator<Long> it = selectionValueIds.iterator();
			while (it.hasNext()) {
				InputFieldSelectionSetValueOutVO selectionSetValue = WebUtil.getInputFieldSelectionSetValue(it.next());
				if (selectionSetValue != null) {
					result.add(selectionSetValue);
				} else {
					break;
				}
			}
		} else {
			result = new ArrayList<InputFieldSelectionSetValueOutVO>();
		}
		return result;
	}

	private Integer textClipMaxLength;

	public InputFieldValueStringAdapter() {
		super();
		this.textClipMaxLength = null;
	}

	public InputFieldValueStringAdapter(Integer textClipMaxLength) {
		super();
		this.textClipMaxLength = textClipMaxLength;
	}

	protected String getCheckboxString(boolean value) {
		if (value) {
			return Messages.getString(MessageCodes.CHECKBOX_CHECKED);
		} else {
			return Messages.getString(MessageCodes.CHECKBOX_UNCHECKED);
		}
	}

	protected DateFormat getDateFormat() {
		return DateUtil.getDateFormat();
	}

	protected  DateFormat getDateTimeFormat() {
		return DateUtil.getDateTimeFormat();
	}

	protected String getSelectionSetValuesSeparator() {
		return SELECTION_SET_VALUES_SEPARATOR;
	}

	protected Integer getTextClipMaxLength() {
		return textClipMaxLength;
	}

	protected DateFormat getTimeFormat() {
		return DateUtil.getTimeFormat();
	}

}
