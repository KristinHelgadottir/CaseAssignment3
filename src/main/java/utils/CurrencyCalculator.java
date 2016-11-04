package utils;

/**
 *
 * @author Diana
 */
// Some of the code we use here is from open source due to time management problem
import java.net.*;
import java.io.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.text.*;
import java.util.*;

public final class CurrencyCalculator {

    private static CurrencyCalculator instance = null;

    private final String nbRatesURL = "http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesHistoryXML?lang=da";

    private File cacheFile = null;

    private String cacheFileName = null;

    private HashMap<String, Long> fxRates = new HashMap<String, Long>(40);

    private Date referenceDate = null;

    private String lastError = null;

    private CurrencyCalculator() {
    }

    //Returns a singleton instance of CurrencyConverter.
    //Return CurrencyConverter instance
    public static CurrencyCalculator getInstance() {
        if (instance == null) {
            instance = new CurrencyCalculator();
        }
        return instance;
    }

//    Converts a double floating point value from one currency to another
    public double convert(double amount, String fromCurrency, String toCurrency)
            throws IOException, ParseException, IllegalArgumentException {
        if (checkCurrencyArgs(fromCurrency, toCurrency)) {
            amount *= fxRates.get(toCurrency);
            amount /= fxRates.get(fromCurrency);
        }
        return amount;
    }

//     Check whether currency arguments are valid and not equal.
//     Return true if both currency arguments are not equal.
    private boolean checkCurrencyArgs(String fromCurrency, String toCurrency)
            throws IOException, ParseException, IllegalArgumentException {
        update();
        if (!fxRates.containsKey(fromCurrency)) {
            throw new IllegalArgumentException(fromCurrency
                    + " currency is not available.");
        }
        if (!fxRates.containsKey(toCurrency)) {
            throw new IllegalArgumentException(toCurrency
                    + " currency is not available.");
        }
        return (!fromCurrency.equals(toCurrency));
    }

//     Check whether the exchange rate for a given currency is available.
//     Return true if exchange rate exists, false otherwise.    
    public boolean isAvailable(String currency) {
        return (fxRates.containsKey(currency));
    }

//     Returns currencies for which exchange rates are available.
//     Return String array with ISO 4217 currency codes.
    public String[] getCurrencies() throws IOException, ParseException {
        if (fxRates.isEmpty()) {
            update();
        }
        String[] currencies = fxRates.keySet().toArray(
                new String[fxRates.size()]);
        return currencies;
    }

//     Get the reference date for the exchange rates as a Java Date.
//     Return Date for which currency exchange rates are valid
    public Date getReferenceDate() {
        return referenceDate;
    }

//     Get the name of the path name of the XML cache file.
//     Return Path name of the XML cache file.
    public String getCacheFileName() {
        return cacheFileName;
    }

//     Set the location where the XML cache file should be stored.
//     see getCacheFileName()
    public void setCacheFileName(String cacheFileName) {
        this.cacheFileName = cacheFileName;
    }

//     Delete XML cache file and reset internal data structure. Calling
//     clearCache() before the convert() method forces a fresh download of the
//     currency exchange rates.
    public void clearCache() {
        initCacheFile();
        cacheFile.delete();
        cacheFile = null;
        referenceDate = null;
    }

//     Check whether cache is initialised and up-to-date. If not, re-download
//     throws ParseException If an error occurs
    private void update() throws IOException, ParseException {
        if (referenceDate == null) {
            initCacheFile();
            if (!cacheFile.exists()) {
                refreshCacheFile();
            }
            parse();
        }
        if (cacheIsExpired()) {
            refreshCacheFile();
            parse();
        }
    }

//     Initialises cache file member variable if not already initialised.
    private void initCacheFile() {
        if (cacheFile == null) {
            if (cacheFileName == null || cacheFileName.equals("")) {
                cacheFileName = System.getProperty("java.io.tmpdir")
                        + "ExchangeRates.xml";
            }
            cacheFile = new File(cacheFileName);
        }
    }

//     Checks whether XML cache file needs to be updated.
//     On weekends, it is 72 hours because no rates are published during
//     weekends.
//     Return true if cache file needs to be updated, false otherwise.
    private boolean cacheIsExpired() {
        final int tolerance = 12;
        if (referenceDate == null) {
            return true;
        }
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        long hoursOld = (cal.getTimeInMillis() - referenceDate.getTime())
                / (1000 * 60 * 60);
        int hoursValid = 24 + tolerance;
        cal.setTime(referenceDate);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            hoursValid = 72;
        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            hoursValid = 48;
        }
        if (hoursOld > hoursValid) {
            return true;
        }
        return false;
    }

//     redownload the XML cache file and store it in a temporary location.
    private void refreshCacheFile() throws IOException {
        lastError = null;
        initCacheFile();
        InputStreamReader in;
        FileWriter out;
        try {
            URL ecbRates = new URL(nbRatesURL);
            in = new InputStreamReader(ecbRates.openStream());
            out = new FileWriter(cacheFile);
            try {
                int c;
                while ((c = in.read()) != -1) {
                    out.write(c);
                }
            } catch (IOException e) {
                lastError = "Read/Write Error: " + e.getMessage();
            } finally {
                out.flush();
                out.close();
                in.close();
            }
        } catch (IOException e) {
            lastError = "Connection/Open Error: " + e.getMessage();
        }
        if (lastError != null) {
            throw new IOException(lastError);
        }
    }

//     Convert a numeric string to a long value with a precision of four digits
//     str Positive numeric string expression.
//     return Value representing 1/10000th of a currency unit.
//     throws NumberFormatException If "str" argument is not numeric.
    private long stringToLong(String str) throws NumberFormatException {
        int decimalPoint = str.indexOf('.');
        String wholePart = "";
        String fractionPart = "";
        if (decimalPoint > -1) {
            if (decimalPoint > 0) {
                wholePart = str.substring(0, decimalPoint);
            }
            fractionPart = str.substring(decimalPoint + 1);
            String padString = "0000";
            int padLength = 4 - fractionPart.length();
            if (padLength > 0) {
                fractionPart += padString.substring(0, padLength);
            } else if (padLength < 0) {
                fractionPart = fractionPart.substring(0, 4);
            }
        } else {
            wholePart = str;
            fractionPart = "0000";
        }
        return (Long.parseLong(wholePart + fractionPart));
    }

//     Parse XML cache file and create internal data structures containing
//     exchange rates and reference dates.
//     throws ParseException If XML file cannot be parsed.
    private void parse() throws ParseException {
        try {
            FileReader input = new FileReader(cacheFile);
            XMLReader saxReader = XMLReaderFactory.createXMLReader();
            DefaultHandler handler = new DefaultHandler() {
                public void startElement(String uri, String localName,
                        String qName, Attributes attributes) {
                    if (localName.equals("Cube")) {
                        String date = attributes.getValue("time");
                        if (date != null) {
                            SimpleDateFormat df = new SimpleDateFormat(
                                    "yyyy-MM-dd HH:mm z");
                            try {
                                referenceDate = df.parse(date + " 14:15 CET");
                            } catch (ParseException e) {
                                lastError = "Cannot parse reference date: "
                                        + date;
                            }
                        }
                        String currency = attributes.getValue("currency");
                        String rate = attributes.getValue("rate");
                        if (currency != null && rate != null) {
                            try {
                                fxRates.put(currency, stringToLong(rate));
                            } catch (Exception e) {
                                lastError = "Cannot parse exchange rate: "
                                        + rate + ". " + e.getMessage();
                            }
                        }
                    }
                }
            };
            lastError = null;
            fxRates.clear();
            fxRates.put("EUR", 10000L);
            saxReader.setContentHandler(handler);
            saxReader.setErrorHandler(handler);
            saxReader.parse(new InputSource(input));
            input.close();
        } catch (Exception e) {
            lastError = "Parser error: " + e.getMessage();
        }
        if (lastError != null) {
            throw new ParseException(lastError, 0);
        }
    }

}

//public class Calculator {
//
//   String rateCode[] = {};
//    double rateAmount[] = new double[rateCode.length];
// 
//    public double exchange(String fromCurrency,String toCurrency,double amount){
//        String rateCode = getRateCode(fromCurrency,toCurrency);
//        double rate = getRate(rateCode);
//        double result = rate * amount;
//        return result;
//    }
// 
//    public void setRate(String fromCurrency, String toCurrency, double rate){
//        String rateCode = getRateCode(fromCurrency,toCurrency);
//        int index = getRateIndex(rateCode);
// 
//            if (index >= 0){
//            rateAmount[index] = rate;
//             }
//    }
// 
//    public void updateRate(String fromCurrency, String toCurrency, double newRate){
//        setRate(fromCurrency, toCurrency, newRate);
//    }
// 
//        private double getRate(String rateCode){
//            if (rateCode == null)
//            return -1;
// 
//        double rate = rateAmount[getRateIndex(rateCode)];
//            return rate;
//        }
// 
//        private String getRateCode(String fromCurrency, String toCurrency){
// 
//            StringBuilder rateCode = new StringBuilder();
// 
//            rateCode.append(fromCurrency.charAt (0)).append(toCurrency.charAt(0));
// 
//            return rateCode.toString().toLowerCase ();
//        }
//    }
