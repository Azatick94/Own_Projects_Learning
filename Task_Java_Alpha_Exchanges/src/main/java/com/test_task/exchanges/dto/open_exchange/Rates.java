
package com.test_task.exchanges.dto.open_exchange;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "AED",
        "AFN",
        "ALL",
        "AMD",
        "ANG",
        "AOA",
        "ARS",
        "AUD",
        "AWG",
        "AZN",
        "BAM",
        "BBD",
        "BDT",
        "BGN",
        "BHD",
        "BIF",
        "BMD",
        "BND",
        "BOB",
        "BRL",
        "BSD",
        "BTC",
        "BTN",
        "BWP",
        "BYN",
        "BZD",
        "CAD",
        "CDF",
        "CHF",
        "CLF",
        "CLP",
        "CNH",
        "CNY",
        "COP",
        "CRC",
        "CUC",
        "CUP",
        "CVE",
        "CZK",
        "DJF",
        "DKK",
        "DOP",
        "DZD",
        "EGP",
        "ERN",
        "ETB",
        "EUR",
        "FJD",
        "FKP",
        "GBP",
        "GEL",
        "GGP",
        "GHS",
        "GIP",
        "GMD",
        "GNF",
        "GTQ",
        "GYD",
        "HKD",
        "HNL",
        "HRK",
        "HTG",
        "HUF",
        "IDR",
        "ILS",
        "IMP",
        "INR",
        "IQD",
        "IRR",
        "ISK",
        "JEP",
        "JMD",
        "JOD",
        "JPY",
        "KES",
        "KGS",
        "KHR",
        "KMF",
        "KPW",
        "KRW",
        "KWD",
        "KYD",
        "KZT",
        "LAK",
        "LBP",
        "LKR",
        "LRD",
        "LSL",
        "LYD",
        "MAD",
        "MDL",
        "MGA",
        "MKD",
        "MMK",
        "MNT",
        "MOP",
        "MRO",
        "MRU",
        "MUR",
        "MVR",
        "MWK",
        "MXN",
        "MYR",
        "MZN",
        "NAD",
        "NGN",
        "NIO",
        "NOK",
        "NPR",
        "NZD",
        "OMR",
        "PAB",
        "PEN",
        "PGK",
        "PHP",
        "PKR",
        "PLN",
        "PYG",
        "QAR",
        "RON",
        "RSD",
        "RUB",
        "RWF",
        "SAR",
        "SBD",
        "SCR",
        "SDG",
        "SEK",
        "SGD",
        "SHP",
        "SLL",
        "SOS",
        "SRD",
        "SSP",
        "STD",
        "STN",
        "SVC",
        "SYP",
        "SZL",
        "THB",
        "TJS",
        "TMT",
        "TND",
        "TOP",
        "TRY",
        "TTD",
        "TWD",
        "TZS",
        "UAH",
        "UGX",
        "USD",
        "UYU",
        "UZS",
        "VES",
        "VND",
        "VUV",
        "WST",
        "XAF",
        "XAG",
        "XAU",
        "XCD",
        "XDR",
        "XOF",
        "XPD",
        "XPF",
        "XPT",
        "YER",
        "ZAR",
        "ZMW",
        "ZWL"
})
public class Rates {

    @JsonProperty("AED")
    private Double aed;
    @JsonProperty("AFN")
    private Double afn;
    @JsonProperty("ALL")
    private Double all;
    @JsonProperty("AMD")
    private Double amd;
    @JsonProperty("ANG")
    private Double ang;
    @JsonProperty("AOA")
    private Double aoa;
    @JsonProperty("ARS")
    private Double ars;
    @JsonProperty("AUD")
    private Double aud;
    @JsonProperty("AWG")
    private Double awg;
    @JsonProperty("AZN")
    private Double azn;
    @JsonProperty("BAM")
    private Double bam;
    @JsonProperty("BBD")
    private Integer bbd;
    @JsonProperty("BDT")
    private Double bdt;
    @JsonProperty("BGN")
    private Double bgn;
    @JsonProperty("BHD")
    private Double bhd;
    @JsonProperty("BIF")
    private Double bif;
    @JsonProperty("BMD")
    private Integer bmd;
    @JsonProperty("BND")
    private Double bnd;
    @JsonProperty("BOB")
    private Double bob;
    @JsonProperty("BRL")
    private Double brl;
    @JsonProperty("BSD")
    private Integer bsd;
    @JsonProperty("BTC")
    private Double btc;
    @JsonProperty("BTN")
    private Double btn;
    @JsonProperty("BWP")
    private Double bwp;
    @JsonProperty("BYN")
    private Double byn;
    @JsonProperty("BZD")
    private Double bzd;
    @JsonProperty("CAD")
    private Double cad;
    @JsonProperty("CDF")
    private Double cdf;
    @JsonProperty("CHF")
    private Double chf;
    @JsonProperty("CLF")
    private Double clf;
    @JsonProperty("CLP")
    private Double clp;
    @JsonProperty("CNH")
    private Double cnh;
    @JsonProperty("CNY")
    private Double cny;
    @JsonProperty("COP")
    private Double cop;
    @JsonProperty("CRC")
    private Double crc;
    @JsonProperty("CUC")
    private Integer cuc;
    @JsonProperty("CUP")
    private Double cup;
    @JsonProperty("CVE")
    private Double cve;
    @JsonProperty("CZK")
    private Double czk;
    @JsonProperty("DJF")
    private Double djf;
    @JsonProperty("DKK")
    private Double dkk;
    @JsonProperty("DOP")
    private Double dop;
    @JsonProperty("DZD")
    private Double dzd;
    @JsonProperty("EGP")
    private Double egp;
    @JsonProperty("ERN")
    private Double ern;
    @JsonProperty("ETB")
    private Double etb;
    @JsonProperty("EUR")
    private Double eur;
    @JsonProperty("FJD")
    private Double fjd;
    @JsonProperty("FKP")
    private Double fkp;
    @JsonProperty("GBP")
    private Double gbp;
    @JsonProperty("GEL")
    private Double gel;
    @JsonProperty("GGP")
    private Double ggp;
    @JsonProperty("GHS")
    private Double ghs;
    @JsonProperty("GIP")
    private Double gip;
    @JsonProperty("GMD")
    private Double gmd;
    @JsonProperty("GNF")
    private Double gnf;
    @JsonProperty("GTQ")
    private Double gtq;
    @JsonProperty("GYD")
    private Double gyd;
    @JsonProperty("HKD")
    private Double hkd;
    @JsonProperty("HNL")
    private Double hnl;
    @JsonProperty("HRK")
    private Double hrk;
    @JsonProperty("HTG")
    private Double htg;
    @JsonProperty("HUF")
    private Double huf;
    @JsonProperty("IDR")
    private Double idr;
    @JsonProperty("ILS")
    private Double ils;
    @JsonProperty("IMP")
    private Double imp;
    @JsonProperty("INR")
    private Double inr;
    @JsonProperty("IQD")
    private Double iqd;
    @JsonProperty("IRR")
    private Integer irr;
    @JsonProperty("ISK")
    private Double isk;
    @JsonProperty("JEP")
    private Double jep;
    @JsonProperty("JMD")
    private Double jmd;
    @JsonProperty("JOD")
    private Double jod;
    @JsonProperty("JPY")
    private Double jpy;
    @JsonProperty("KES")
    private Double kes;
    @JsonProperty("KGS")
    private Double kgs;
    @JsonProperty("KHR")
    private Double khr;
    @JsonProperty("KMF")
    private Double kmf;
    @JsonProperty("KPW")
    private Integer kpw;
    @JsonProperty("KRW")
    private Double krw;
    @JsonProperty("KWD")
    private Double kwd;
    @JsonProperty("KYD")
    private Double kyd;
    @JsonProperty("KZT")
    private Double kzt;
    @JsonProperty("LAK")
    private Double lak;
    @JsonProperty("LBP")
    private Double lbp;
    @JsonProperty("LKR")
    private Double lkr;
    @JsonProperty("LRD")
    private Double lrd;
    @JsonProperty("LSL")
    private Double lsl;
    @JsonProperty("LYD")
    private Double lyd;
    @JsonProperty("MAD")
    private Double mad;
    @JsonProperty("MDL")
    private Double mdl;
    @JsonProperty("MGA")
    private Double mga;
    @JsonProperty("MKD")
    private Double mkd;
    @JsonProperty("MMK")
    private Double mmk;
    @JsonProperty("MNT")
    private Double mnt;
    @JsonProperty("MOP")
    private Double mop;
    @JsonProperty("MRO")
    private Double mro;
    @JsonProperty("MRU")
    private Double mru;
    @JsonProperty("MUR")
    private Double mur;
    @JsonProperty("MVR")
    private Double mvr;
    @JsonProperty("MWK")
    private Double mwk;
    @JsonProperty("MXN")
    private Double mxn;
    @JsonProperty("MYR")
    private Double myr;
    @JsonProperty("MZN")
    private Double mzn;
    @JsonProperty("NAD")
    private Double nad;
    @JsonProperty("NGN")
    private Double ngn;
    @JsonProperty("NIO")
    private Double nio;
    @JsonProperty("NOK")
    private Double nok;
    @JsonProperty("NPR")
    private Double npr;
    @JsonProperty("NZD")
    private Double nzd;
    @JsonProperty("OMR")
    private Double omr;
    @JsonProperty("PAB")
    private Integer pab;
    @JsonProperty("PEN")
    private Double pen;
    @JsonProperty("PGK")
    private Double pgk;
    @JsonProperty("PHP")
    private Double php;
    @JsonProperty("PKR")
    private Double pkr;
    @JsonProperty("PLN")
    private Double pln;
    @JsonProperty("PYG")
    private Double pyg;
    @JsonProperty("QAR")
    private Double qar;
    @JsonProperty("RON")
    private Double ron;
    @JsonProperty("RSD")
    private Double rsd;
    @JsonProperty("RUB")
    private Double rub;
    @JsonProperty("RWF")
    private Double rwf;
    @JsonProperty("SAR")
    private Double sar;
    @JsonProperty("SBD")
    private Double sbd;
    @JsonProperty("SCR")
    private Double scr;
    @JsonProperty("SDG")
    private Double sdg;
    @JsonProperty("SEK")
    private Double sek;
    @JsonProperty("SGD")
    private Double sgd;
    @JsonProperty("SHP")
    private Double shp;
    @JsonProperty("SLL")
    private Double sll;
    @JsonProperty("SOS")
    private Double sos;
    @JsonProperty("SRD")
    private Double srd;
    @JsonProperty("SSP")
    private Double ssp;
    @JsonProperty("STD")
    private Double std;
    @JsonProperty("STN")
    private Double stn;
    @JsonProperty("SVC")
    private Double svc;
    @JsonProperty("SYP")
    private Double syp;
    @JsonProperty("SZL")
    private Double szl;
    @JsonProperty("THB")
    private Double thb;
    @JsonProperty("TJS")
    private Double tjs;
    @JsonProperty("TMT")
    private Double tmt;
    @JsonProperty("TND")
    private Double tnd;
    @JsonProperty("TOP")
    private Double top;
    @JsonProperty("TRY")
    private Double _try;
    @JsonProperty("TTD")
    private Double ttd;
    @JsonProperty("TWD")
    private Double twd;
    @JsonProperty("TZS")
    private Double tzs;
    @JsonProperty("UAH")
    private Double uah;
    @JsonProperty("UGX")
    private Double ugx;
    @JsonProperty("USD")
    private Integer usd;
    @JsonProperty("UYU")
    private Double uyu;
    @JsonProperty("UZS")
    private Double uzs;
    @JsonProperty("VES")
    private Double ves;
    @JsonProperty("VND")
    private Double vnd;
    @JsonProperty("VUV")
    private Double vuv;
    @JsonProperty("WST")
    private Double wst;
    @JsonProperty("XAF")
    private Double xaf;
    @JsonProperty("XAG")
    private Double xag;
    @JsonProperty("XAU")
    private Double xau;
    @JsonProperty("XCD")
    private Double xcd;
    @JsonProperty("XDR")
    private Double xdr;
    @JsonProperty("XOF")
    private Double xof;
    @JsonProperty("XPD")
    private Double xpd;
    @JsonProperty("XPF")
    private Double xpf;
    @JsonProperty("XPT")
    private Double xpt;
    @JsonProperty("YER")
    private Double yer;
    @JsonProperty("ZAR")
    private Double zar;
    @JsonProperty("ZMW")
    private Double zmw;
    @JsonProperty("ZWL")
    private Integer zwl;
}
