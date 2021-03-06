package com.sismics.util;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang.StringUtils;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Map.Entry;

/**
 * Date utilities.
 * 
 * @author jtremeaux
 */
public class DateUtil {
    private static final Logger log = LoggerFactory.getLogger(DateUtil.class);

    private final static ImmutableMap<String, String> TIMEZONE_CODE_MAP = new ImmutableMap.Builder<String, String>()
            .put("ACDT", " +10:30")
            .put("ACST", " +09:30")
            .put("ACT", " +08")
            .put("ADT", " -03")
            .put("AEDT", " +11")
            .put("AEST", " +10")
            .put("AFT", " +04:30")
            .put("AKDT", " -08")
            .put("AKST", " -09")
            .put("AMST", " +05")
            .put("AMT", " +04")
            .put("ART", " -03")
//            .put("AST", " +03")
            .put("AST", " -04")
            .put("AWDT", " +09")
            .put("AWST", " +08")
            .put("AZOST", " -01")
            .put("AZT", " +04")
            .put("BDT", " +08")
            .put("BIOT", " +06")
            .put("BIT", " -12")
            .put("BOT", " -04")
            .put("BRT", " -03")
//            .put("BST", " +06")
            .put("BST", " +01")
            .put("BTT", " +06")
            .put("CAT", " +02")
            .put("CCT", " +06:30")
            .put("CDT", " -05")
//            .put("CDT", " -04")
            .put("CEDT", " +02")
            .put("CEST", " +02")
            .put("CET", " +01")
            .put("CHADT", " +13:45")
            .put("CHAST", " +12:45")
            .put("CHOT", " -08")
            .put("ChST", " +10")
            .put("CHUT", " +10")
            .put("CIST", " -08")
            .put("CIT", " +08")
            .put("CKT", " -10")
            .put("CLST", " -03")
            .put("CLT", " -04")
            .put("COST", " -04")
            .put("COT", " -05")
            .put("CST", " -06")
//            .put("CST", " +08")
//            .put("CST", " +09:30")
//            .put("CST", " +10:30")
//            .put("CST", " -05")
            .put("CT", " +08")
            .put("CVT", " -01")
            .put("CWST", " +08:45")
            .put("CXT", " +07")
            .put("DAVT", " +07")
            .put("DDUT", " +10")
            .put("DFT", " +01")
            .put("EASST", " -05")
            .put("EAST", " -06")
            .put("EAT", " +03")
//            .put("ECT", " -04")
            .put("ECT", " -05")
            .put("EDT", " -04")
            .put("EEDT", " +03")
            .put("EEST", " +03")
            .put("EET", " +02")
            .put("EGST", " +00")
            .put("EGT", " -01")
            .put("EIT", " +09")
            .put("EST", " -05")
//            .put("EST", " +10")
            .put("FET", " +03")
            .put("FJT", " +12")
            .put("FKST", " -03")
            .put("FKT", " -04")
            .put("FNT", " -02")
            .put("GALT", " -06")
            .put("GAMT", " -09")
            .put("GET", " +04")
            .put("GFT", " -03")
            .put("GILT", " +12")
            .put("GIT", " -09")
            .put("GMT", " +00")
//            .put("GST", " -02")
            .put("GST", " +04")
            .put("GYT", " -04")
            .put("HADT", " -09")
            .put("HAEC", " +02")
            .put("HAST", " -10")
            .put("HKT", " +08")
            .put("HMT", " +05")
            .put("HOVT", " +07")
            .put("HST", " -10")
            .put("ICT", " +07")
            .put("IDT", " +03")
            .put("IOT", " +03")
            .put("IRDT", " +08")
            .put("IRKT", " +09")
            .put("IRST", " +03:30")
            .put("IST", " +05:30")
//            .put("IST", " +01")
//            .put("IST", " +02")
            .put("JST", " +09")
            .put("KGT", " +06")
            .put("KOST", " +11")
            .put("KRAT", " +07")
            .put("KST", " +09")
            .put("LHST", " +10:30")
//            .put("LHST", " +11")
            .put("LINT", " +14")
            .put("MAGT", " +12")
            .put("MART", " -09:30")
            .put("MAWT", " +05")
            .put("MDT", " -06")
            .put("MET", " +01")
            .put("MEST", " +02")
            .put("MHT", " +12")
            .put("MIST", " +11")
            .put("MIT", " -09:30")
            .put("MMT", " +06:30")
            .put("MSK", " +04")
//            .put("MST", " +08")
            .put("MST", " -07")
//            .put("MST", " +06:30")
            .put("MUT", " +04")
            .put("MVT", " +05")
            .put("MYT", " +08")
            .put("NCT", " +11")
            .put("NDT", " -02:30")
            .put("NFT", " +11:30")
            .put("NPT", " +05:45")
            .put("NST", " -03:30")
            .put("NT", " -03:30")
            .put("NUT", " -11:30")
            .put("NZDT", " +13")
            .put("NZST", " +12")
            .put("OMST", " +06")
            .put("ORAT", " +05")
            .put("PDT", " -07")
            .put("PET", " -05")
            .put("PETT", " +12")
            .put("PGT", " +10")
            .put("PHOT", " +13")
            .put("PHT", " +08")
            .put("PKT", " +05")
            .put("PMDT", " -02")
            .put("PMST", " -03")
            .put("PONT", " +11")
            .put("PST", " -08")
//            .put("PST", " +08")
            .put("RET", " +04")
            .put("ROTT", " -03")
            .put("SAKT", " +11")
            .put("SAMT", " +04")
            .put("SAST", " +02")
            .put("SBT", " +11")
            .put("SCT", " +04")
            .put("SGT", " +08")
            .put("SLT", " +05:30")
            .put("SRT", " -03")
            .put("SST", " -11")
//            .put("SST", " +08")
            .put("SYOT", " +03")
            .put("TAHT", " -10")
            .put("THA", " +07")
            .put("TFT", " +05")
            .put("TJT", " +05")
            .put("TKT", " +14")
            .put("TLT", " +09")
            .put("TMT", " +05")
            .put("TOT", " +13")
            .put("TVT", " +12")
            .put("UCT", " +00")
            .put("ULAT", " +08")
            .put("UTC", " +00")
            .put("UYST", " -02")
            .put("UYT", " -03")
            .put("UZT", " +05")
            .put("VET", " -04:30")
            .put("VLAT", " +10")
            .put("VOLT", " +04")
            .put("VOST", " +06")
            .put("VUT", " +11")
            .put("WAKT", " +12")
            .put("WAST", " +02")
            .put("WAT", " +01")
            .put("WEDT", " +01")
            .put("WEST", " +01")
            .put("WET", " +00")
            .put("WST", " +08")
            .put("YAKT", " +09")
            .put("YEKT", " +05")
            .build();
    
    /**
     * Try to guess the timezone code, and replace it with a timezone offset.
     * Note: JodaTime can guess a few codes already, they didn't include all codes since they are not standardized.
     * This method should only be used in last resort.
     * 
     * @param date Formated date, supposedly ending with a timezone code
     * @return Date with the code replaced by its offset if there is a match
     */
    public static String guessTimezoneOffset(String date) {
        for (Entry<String, String> entry : TIMEZONE_CODE_MAP.entrySet()) {
            String code = entry.getKey();
            String offset = entry.getValue();
            String pattern = " \\(?" + code + "\\)?";
            if (date.matches(".+" + pattern)) {
                return date.replaceAll(pattern, offset);
            }
        }
        return date;
    }

    /**
     * Extract a date from a string format.
     *
     * @param date The date to parse
     * @param df Formatter
     * @return Date or null is the date is unparsable
     */
    public static Date parseDate(String date, DateTimeFormatter df) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        try {
            return df.parseDateTime(date).toDate();
        } catch (IllegalArgumentException e) {
            // NOP
        }
        String dateWithOffset = guessTimezoneOffset(date);
        if (!dateWithOffset.equals(date)) {
            try {
                return df.parseDateTime(dateWithOffset).toDate();
            } catch (IllegalArgumentException e) {
                // NOP
            }
        }

        if (log.isWarnEnabled()) {
            log.warn(MessageFormat.format("Error parsing date: {0}", date));
        }
        return null;
    }
}
