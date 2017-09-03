package org.phoenixctms.ctsms.executable.xls;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.phoenixctms.ctsms.domain.Asp;
import org.phoenixctms.ctsms.domain.AspAtcCode;
import org.phoenixctms.ctsms.domain.AspAtcCodeDao;
import org.phoenixctms.ctsms.domain.AspDao;
import org.phoenixctms.ctsms.domain.AspSubstance;
import org.phoenixctms.ctsms.domain.AspSubstanceDao;
import org.phoenixctms.ctsms.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class AspRowProcessor extends RowProcessor {

	private static final int NAME_COLUMN_INDEX = 0;
	private static final int TYPE_COLUMN_INDEX = 1;
	private static final int LABELING_COLUMN_INDEX = 2;
	private static final int REGISTRATION_NUMBER_COLUMN_INDEX = 3;
	private static final int PROPRIETOR_COLUMN_INDEX = 4;
	private static final int REGISTRATION_DATE_COLUMN_INDEX = 5;
	private static final int SUBSTANCES_COLUMN_INDEX = 6;
	private static final int ATC_CODE_COLUMN_INDEX = 7;
	private static final int BATCH_RELEASE_COLUMN_INDEX = 8;
	private static final int BATCH_TESTING_COLUMN_INDEX = 9;
	private static final int BATCH_TESTING_EXCLUSION_COLUMN_INDEX = 10;
	private static final int PRESCRIPTION_COLUMN_INDEX = 11;
	private static final int DISTRIBUTION_COLUMN_INDEX = 12;
	private static final int HUMAN_COLUMN_INDEX = 13;
	private static final int CATEGORY_COLUMN_INDEX = 14;

	// private static final int SPECIES_COLUMN_INDEX = 15;
	// private static final int SPECIES_CATEGORY_COLUMN_INDEX = 16;
	// private static final int APPLICATION_COLUMN_INDEX = 17;
	// private static final int DOSING_COLUMN_INDEX = 18;
	// private static final int TISSUE_COLUMN_INDEX = 19;
	// private static final int DEPLETION_TIME_VALUE_COLUMN_INDEX = 20;
	// private static final int DEPLETION_TIME_UNIT_COLUMN_INDEX = 21;
	// private static final int NOTE_COLUMN_INDEX = 22;

	private static boolean isAsp(String value) {
		return "arzneispezialität".equals(value.toLowerCase());
	}

	private static boolean isHuman(String value) {
		return "human".equals(value.toLowerCase()) || value.length() == 0;
	}

	private static boolean parseBoolean(String value) {
		if ("1".equals(value)) {
			return true;
		} else if ("0".equals(value)) {
			return false;
		} else {
			throw new IllegalArgumentException("cannot parse boolean value " + value);
		}
	}

	private static java.util.Date parseDate(String value) {
		SimpleDateFormat sdfToDate = new SimpleDateFormat("yyyy-MM-dd");
		try {
			// return new java.sql.Date(sdfToDate.parse(value).getTime());
			return sdfToDate.parse(value);
		} catch (ParseException e) {
			throw new IllegalArgumentException("cannot parse date value " + value, e);
		}
	}

	private int nameColumnIndex;
	private int typeColumnIndex;
	private int labelingColumnIndex;
	private int registrationNumberColumnIndex;
	private int proprietorColumnIndex;
	private int registrationDateColumnIndex;
	private int substancesColumnIndex;
	private int atcCodeColumnIndex;
	private int batchReleaseColumnIndex;
	private int batchTestingColumnIndex;
	private int batchTestingExclusionColumnIndex;
	private int prescriptionColumnIndex;
	private int distributionColumnIndex;
	private int humanColumnIndex;
	private int categoryColumnIndex;
	// private int speciesColumnIndex;
	// private int speciesCategoryColumnIndex;
	// private int applicationColumnIndex;
	// private int dosingColumnIndex;
	// private int tissueColumnIndex;
	// private int depletionTimeValueColumnIndex;
	// private int depletionTimeUnitColumnIndex;
	// private int noteColumnIndex;

	private TreeMap<String, ArrayList<Asp>> substancesMap;
	// private TreeMap<String, ArrayList<Asp>> atcCodesMap;

	private final static Pattern SUBSTANCES_SEPARATOR_REGEXP = Pattern.compile(";");
	private final static Pattern ATC_CODES_SEPARATOR_REGEXP = Pattern.compile(";");

	@Autowired
	protected AspDao aspDao;
	@Autowired
	protected AspSubstanceDao aspSubstanceDao;
	@Autowired
	protected AspAtcCodeDao aspAtcCodeDao;
	private String revision;

	// private int revision
	public AspRowProcessor() {
		super();
		filterDupes = true;
		//useComments = false;
		acceptCommentsIndex = 0;
		substancesMap = new TreeMap<String, ArrayList<Asp>>();
		// atcCodesMap = new TreeMap<String, ArrayList<Asp>>();
	}

	private Asp createAsp(String[] values) {

		Asp asp = Asp.Factory.newInstance();
		asp.setName(getName(values));
		// asp.setType(getType(values));
		asp.setLabeling(getLabeling(values));
		asp.setRegistrationNumber(getRegistrationNumber(values));
		asp.setProprietor(getProprietor(values));
		asp.setRegistrationDate(parseDate(getRegistrationDate(values)));
		// asp.setAtcCode(getAtcCode(values));
		asp.setBatchRelease(CommonUtil.isEmptyString(getBatchRelease(values)) ? null : parseBoolean(getBatchRelease(values)));
		asp.setBatchTesting(CommonUtil.isEmptyString(getBatchTesting(values)) ? null : parseBoolean(getBatchTesting(values)));
		asp.setBatchTestingExclusion(CommonUtil.isEmptyString(getBatchTestingExclusion(values)) ? null : parseBoolean(getBatchTestingExclusion(values)));
		asp.setPrescription(getPrescription(values));
		asp.setDistribution(getDistribution(values));
		asp.setHuman(isHuman(getHuman(values)));
		asp.setCategory(getCategory(values));
		// asp.setSpecies(getSpecies(values));
		// asp.setSpeciesCategory(getSpeciesCategory(values));
		// asp.setApplication(getApplication(values));
		// asp.setDosing(getDosing(values));
		// asp.setTissue(getTissue(values));
		// asp.setDepletionTimeValue(CommonUtil.isEmptyString(getDepletionTimeValue(values)) ? null : Float.parseFloat(getDepletionTimeValue(values)));
		// asp.setDepletionTimeUnit(getDepletionTimeUnit(values));
		// asp.setNote(getNote(values));
		asp.setRevision(revision);
		asp = aspDao.create(asp);
		createAtcCodes(asp, values);
		return asp;
	}

	private HashSet<AspAtcCode> createAtcCodes(Asp asp, String[] values) {
		HashSet<AspAtcCode> aspAtcCodes = new HashSet<AspAtcCode>();
		String[] atcCodes = ATC_CODES_SEPARATOR_REGEXP.split(getAtcCode(values), -1);
		for (int i = 0; i < atcCodes.length; i++) {
			String atcCode = atcCodes[i].trim();
			if (!CommonUtil.isEmptyString(atcCode)) {
				AspAtcCode aspAtcCode = AspAtcCode.Factory.newInstance();
				aspAtcCode.setAsp(asp);
				aspAtcCode.setCode(atcCode);
				aspAtcCode.setRevision(revision);
				asp.addAtcCodes(aspAtcCodeDao.create(aspAtcCode));
			}
		}
		return aspAtcCodes;
	}

	// private String getApplication(String[] values) {
	// return getColumnValue(values, applicationColumnIndex);
	// }

	private String getAtcCode(String[] values) {
		return getColumnValue(values, atcCodeColumnIndex);
	}

	private String getBatchRelease(String[] values) {
		return getColumnValue(values, batchReleaseColumnIndex);
	}

	private String getBatchTesting(String[] values) {
		return getColumnValue(values, batchTestingColumnIndex);
	}

	private String getBatchTestingExclusion(String[] values) {
		return getColumnValue(values, batchTestingExclusionColumnIndex);
	}

	private String getCategory(String[] values) {
		return getColumnValue(values, categoryColumnIndex);
	}

	private String getDistribution(String[] values) {
		return getColumnValue(values, distributionColumnIndex);
	}

	// private String getDepletionTimeUnit(String[] values) {
	// return getColumnValue(values, depletionTimeUnitColumnIndex);
	// }
	//
	// private String getDepletionTimeValue(String[] values) {
	// return getColumnValue(values, depletionTimeValueColumnIndex);
	// }
	//
	// private String getDosing(String[] values) {
	// return getColumnValue(values, dosingColumnIndex);
	// }

	private String getHuman(String[] values) {
		return getColumnValue(values, humanColumnIndex);
	}

	private String getLabeling(String[] values) {
		return getColumnValue(values, labelingColumnIndex);
	}

	private String getName(String[] values) {
		return getColumnValue(values, nameColumnIndex);
	}

	// private String getNote(String[] values) {
	// return getColumnValue(values, noteColumnIndex);
	// }

	private String getPrescription(String[] values) {
		return getColumnValue(values, prescriptionColumnIndex);
	}

	private String getProprietor(String[] values) {
		return getColumnValue(values, proprietorColumnIndex);
	}

	private String getRegistrationDate(String[] values) {
		return getColumnValue(values, registrationDateColumnIndex);
	}

	private String getRegistrationNumber(String[] values) {
		return getColumnValue(values, registrationNumberColumnIndex);
	}

	public String getRevision() {
		return revision;
	}

	// private String getSpecies(String[] values) {
	// return getColumnValue(values, speciesColumnIndex);
	// }
	//
	// private String getSpeciesCategory(String[] values) {
	// return getColumnValue(values, speciesCategoryColumnIndex);
	// }

	private String getSubstances(String[] values) {
		return getColumnValue(values, substancesColumnIndex);
	}

	// private String getTissue(String[] values) {
	// return getColumnValue(values, tissueColumnIndex);
	// }

	private String getType(String[] values) {
		return getColumnValue(values, typeColumnIndex);
	}

	@Override
	public void init() throws Throwable {
		super.init();
		nameColumnIndex = NAME_COLUMN_INDEX;
		typeColumnIndex = TYPE_COLUMN_INDEX;
		labelingColumnIndex = LABELING_COLUMN_INDEX;
		registrationNumberColumnIndex = REGISTRATION_NUMBER_COLUMN_INDEX;
		proprietorColumnIndex = PROPRIETOR_COLUMN_INDEX;
		registrationDateColumnIndex = REGISTRATION_DATE_COLUMN_INDEX;
		substancesColumnIndex = SUBSTANCES_COLUMN_INDEX;
		atcCodeColumnIndex = ATC_CODE_COLUMN_INDEX;
		batchReleaseColumnIndex = BATCH_RELEASE_COLUMN_INDEX;
		batchTestingColumnIndex = BATCH_TESTING_COLUMN_INDEX;
		batchTestingExclusionColumnIndex = BATCH_TESTING_EXCLUSION_COLUMN_INDEX;
		prescriptionColumnIndex = PRESCRIPTION_COLUMN_INDEX;
		distributionColumnIndex = DISTRIBUTION_COLUMN_INDEX;
		humanColumnIndex = HUMAN_COLUMN_INDEX;
		categoryColumnIndex = CATEGORY_COLUMN_INDEX;
		// speciesColumnIndex = SPECIES_COLUMN_INDEX;
		// speciesCategoryColumnIndex = SPECIES_CATEGORY_COLUMN_INDEX;
		// applicationColumnIndex = APPLICATION_COLUMN_INDEX;
		// dosingColumnIndex = DOSING_COLUMN_INDEX;
		// tissueColumnIndex = TISSUE_COLUMN_INDEX;
		// depletionTimeValueColumnIndex = DEPLETION_TIME_VALUE_COLUMN_INDEX;
		// depletionTimeUnitColumnIndex = DEPLETION_TIME_UNIT_COLUMN_INDEX;
		// noteColumnIndex = NOTE_COLUMN_INDEX;
		substancesMap.clear();
		// atcCodesMap.clear();
	}

	@Override
	protected int lineHashCode(String[] values) {
		return new HashCodeBuilder(1249046965, -82296885)
		// .append(getRegistrationNumber(values))
		.append(getName(values))
		.append(getType(values))
		.append(getLabeling(values))
		.append(getRegistrationNumber(values))
		.append(getProprietor(values))
		.append(getRegistrationDate(values))
		.append(getSubstances(values))
		.append(getAtcCode(values))
		.append(getBatchRelease(values))
		.append(getBatchTesting(values))
		.append(getBatchTestingExclusion(values))
		.append(getPrescription(values))
		.append(getDistribution(values))
		.append(getHuman(values))
		.append(getCategory(values))
		// .append(getSpecies(values))
		// .append(getSpeciesCategory(values))
		// .append(getApplication(values))
		// .append(getDosing(values))
		// .append(getTissue(values))
		// .append(getDepletionTimeValue(values))
		// .append(getDepletionTimeUnit(values))
		// .append(getNote(values))
		.toHashCode();
	}

	@Override
	protected void postProcess() {
		Iterator<String> substancesIt = substancesMap.keySet().iterator();
		while (substancesIt.hasNext()) {
			String substance = substancesIt.next();
			AspSubstance aspSubstance = AspSubstance.Factory.newInstance();
			aspSubstance.setName(substance);
			aspSubstance.setRevision(revision);
			ArrayList<Asp> asps = substancesMap.get(substance);
			Iterator<Asp> aspsIt = asps.iterator();
			while (aspsIt.hasNext()) {
				aspSubstance.addAsps(aspsIt.next());
			}
			aspSubstance = aspSubstanceDao.create(aspSubstance);
			jobOutput.println("substance '" + substance + "' added: " + asps.size() + " asp(s)");
		}
		// Iterator<String> atcCodesIt = atcCodesMap.keySet().iterator();
		// while (atcCodesIt.hasNext()) {
		// String atcCode = atcCodesIt.next();
		// AspAtcCode aspAtcCode = AspAtcCode.Factory.newInstance();
		// aspAtcCode.setCode(atcCode);
		// aspAtcCode.setRevision(revision);
		// ArrayList<Asp> asps = atcCodesMap.get(atcCode);
		// Iterator<Asp> aspsIt = asps.iterator();
		// while (aspsIt.hasNext()) {
		// aspAtcCode.addAsps(aspsIt.next());
		// }
		// aspAtcCode = aspAtcCodeDao.create(aspAtcCode);
		// jobOutput.println("atc code '" + atcCode + "' added: " + asps.size() + " asp(s)");
		// }
	}

	@Override
	protected int processRow(String[] values, long rowNumber) throws Throwable {
		// if (isAsp(getType(values))) {
		Asp asp = createAsp(values);
		String[] substances = SUBSTANCES_SEPARATOR_REGEXP.split(getSubstances(values), -1);
		for (int i = 0; i < substances.length; i++) {
			String substance = substances[i].trim();
			if (!CommonUtil.isEmptyString(substance)) {
				ArrayList<Asp> asps;
				if (substancesMap.containsKey(substance)) {
					asps = substancesMap.get(substance);
				} else {
					asps = new ArrayList<Asp>();
					substancesMap.put(substance, asps);
				}
				asps.add(asp);
				// } else {
				// jobOutput.println("substance '" + substance + "' added: " + asps.size() + " asp(s)");
			}
		}
		return 1;
		// }
		// return 0;
	}

	public void setRevision(String revision) {
		jobOutput.println("import as revision " + revision);
		this.revision = revision;
	}

	@Override
	protected boolean testNotNullRowFields(String[] values, long rowNumber) {
		if (CommonUtil.isEmptyString(getType(values))) {
			jobOutput.println("row " + rowNumber + ": empty type: " + getName(values));
			return false;
		}
		if (!isAsp(getType(values))) {
			// jobOutput.println("row " + rowNumber + ": type '" + getType(values) + "' skipped");
			return false;
		}
		if (CommonUtil.isEmptyString(getName(values))) {
			jobOutput.println("row " + rowNumber + ": empty name");
			return false;
		}
		if (CommonUtil.isEmptyString(getLabeling(values))) {
			jobOutput.println("row " + rowNumber + ": empty labeling: " + getName(values));
			return false;
		}
		if (CommonUtil.isEmptyString(getRegistrationNumber(values))) {
			jobOutput.println("row " + rowNumber + ": empty registration number: " + getName(values));
			return false;
		}
		// if (CommonUtil.isEmptyString(getProprietor(values))) {
		// jobOutput.println("row " + rowNumber + ": empty proprietor: " + getName(values));
		// return false;
		// }
		if (CommonUtil.isEmptyString(getRegistrationDate(values))) {
			jobOutput.println("row " + rowNumber + ": empty registration date: " + getName(values));
			return false;
		}
		// if (CommonUtil.isEmptyString(getPrescription(values))) {
		// jobOutput.println("row " + rowNumber + ": empty prescription: " + getName(values));
		// return false;
		// }
		// if (CommonUtil.isEmptyString(getDistribution(values))) {
		// jobOutput.println("row " + rowNumber + ": empty distribution: " + getName(values));
		// return false;
		// }
		// if (CommonUtil.isEmptyString(getHuman(values))) {
		// jobOutput.println("row " + rowNumber + ": empty human/veterinary: " + getName(values));
		// return false;
		// }
		// if (CommonUtil.isEmptyString(getCategory(values))) {
		// jobOutput.println("row " + rowNumber + ": empty category: " + getName(values));
		// return false;
		// }
		return true;
	}



}
