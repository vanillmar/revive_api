package com.example.revive_app.data;

import com.example.revive_app.model.Question;
import com.example.revive_app.model.Subject;

import java.util.List;

public class InstrumentRatingQuestions {

    // Assuming 'instrumentRating' is a Category or similar object, as in the example
    // You may need to define the Question class if not already defined, e.g.:
    // public record Question(String id, String question, List<String> options, int answerIndex, Object category) {}

    public static List<Question> getQuestions(Subject instrumentRating) {
        return List.of(
            new Question(
                null,
                "Course 235(M) MSA 7800 ft QNH 995 hPa Temp. ISA o The minimum suitable IFR flight level is: o FL60 FL90",
                List.of("FL75", "FL100", "o Question 2 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "The minimum sector altitude (MSA) on an instrument apppraoch chart is referenced to a radio navigation facility, usually within… o 5 NM 30 NM",
                List.of("25 NM", "20 NM", "o Question 3 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "QNH 1025 HPa ISA +10 deg. C IFR flight 135° magnetic course airway MSA 7800 ft o The minimum flight level is: o FL75 FL65",
                List.of("FL90", "FL80", "o Question 4 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "Excluding RVSM, an appropriate flight level (FL) for an IFR flight in accordance with the semi-circular height rules on a Magnetic Course 200 is: o FL320 FL310",
                List.of("FL300", "FL290", "o Question 5 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "Excluding RVSM, an appropriate flight level (FL) for an IFR flight in accordance with the semi-circular height rules on a Magnetic Course 180 is: o FL85 FL90",
                List.of("FL100", "FL115", "o Question 6 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See attachment IC-033-032) When following the track 185(M) from TALLA (TLA N55°30.0′, W003°21.2′), the aircraft is following what type of route? o RNAV route RNP route",
                List.of("Non-RNAV route", "Direct route", "o Question 7 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See attachment ic-033-095) The airway with designator UG851 is what type of airway? o Conditional route RNAV ATS route",
                List.of("Route usable by non B-RNAV equipped aircraft", "Direct route", "o Question 8 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "o According GM1 CAT.OP.MPA.145 (b), MOCA is the sum of the maximum terrain or obstacle elevation plus (1) ____ for elevation up to and including 6000 ft, or (2) ____ for elevation exceeding 6000 ft rounded up to the next 100 ft. o (1) 3000 ft, (2) 2000 ft (1) 500 ft, (2) 1000 ft",
                List.of("(1) 1000 ft, (2) 2000 ft", "(1) 2000 ft, (2) 1000 ft", "o Question 9 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "According GM1 CAT.OP.MPA.145 (b), the lowest MOCA to be indicated is… o 1000 ft (300 m) 500 ft (150 m)",
                List.of("2000 ft (600 m)", "5000 ft (450 m)", "o Question 10 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "An airway is marked “3500T 2100 a” means the: o Minimum En-route Altitude (MEA) is 3500 ft. Airway is a low level link route from 2100 ft to 3500 ft AMSL",
                List.of("Minimum Obstacle Clearance Altitude (MOCA) is 3500 ft.", "Base of the Airway is 3500 ft MSL.", "o Question 11 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "An airway is marked “FL80 1500 a” means the… o Base of the airway is 1500 ft MSL. Airway extends from 1500 ft MSL to FL80.",
                List.of("Minimum En-route Altitude (MEA) is FL80.", "Minimum radio reception altitude (MRA) is 1500 ft AMSL.", "o Question 12 of 122", "o ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "An airway is marked “5000 2900a” means the… o Maximum Authorised Altitude (MAA). Minimum En-route Altitude (MEA).",
                List.of("Minimum Obstacle Clearance Altitude (MOCA).", "Minimum Holding Altitude (MHA).", "o Question 13 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "The lowest altitude between radio fixes assuring navigational signal coverage and obstacle clearance is the: o Minimum Off Route Altitude (MORA) Minimum Obstacle Clearance Altitude (MOCA)",
                List.of("Minimum Sector Altitude (MSA)", "Minimum Enroute Altitude (MEA)", "o Question 14 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "The Minimum Off-Route Altitude (MORA) provides obstruction clearance up to what distance from the airway centerline? o 20 NM 5 NM",
                List.of("1 NM", "10 NM", "o Question 15 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "During IFR descent to an airport, who is responsible for terrain separation and obstacle clearance? o The pilot in command (PIC) in all cases The pilot in command (PIC), except when flying in IMC",
                List.of("The pilot in command (PIC), except the flight is RADAR vectored", "ATC in all cases", "o Question 16 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "According ICAO Doc 82168, in non-mountainous areas (e.g with terrain not higher than 3000 ft), the minimum permissible holding level provides a clearance of at least (1) ____ above obstacles in the holding area, and a clearance which ranges from 300 m at the edge of the holding area to a minimum of 60 m at the (2) ____ limit of the buffer area. o (1) 600 m, (2) 5 NM (1) 300 m, (2) 5 NM",
                List.of("(1) 300 m, (2) 10 NM", "(1) 600 m, (2) 10 NM", "o Question 17 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "For an airway in mountainous terrain, the approximate minimum clearance used for calculating MOCA is (1) ____ for elevations less than 5000 ft and (2) ____ for elevations greater than 5000 ft. o (1) 1000 ft, (2) 3000 ft (1) 1000 ft, (2) 2000 ft",
                List.of("(1) 1500 ft, (2) 2000 ft", "(1) 1500 ft, (2) 3000 ft", "o Question 18 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "o According SERA.5015, an IFR flight shall be flown at a level which is not below the minimum flight altitude, or, where no such minimum flight altitude has been established: over high terrain or in mountainous areas, at a level which is at least (1) ____ above the highest obstacle located within (2) ____ of the estimated position of the aircraft. o (1) 300 m (1000 ft), (2) 8 km (1) 300 m (1000 ft), (2) 5 km",
                List.of("(1) 600 m (2000 ft), (2) 5 km", "(1) 600 m (2000 ft), (2) 8 km", "o Question 19 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "o According SERA.5015, an IFR flight shall be flown at a level which is not below the minimum flight altitude, or, where no such minimum flight altitude has been established: if NOT over high terrain or in mountainous areas, at a level which is at least (1) ____ above the highest obstacle located within (2) ____ of the estimated position of the aircraft. o (1) 300 m (1000 ft), (2) 8 km (1) 300 m (1000 ft), (2) 5 km",
                List.of("(1) 600 m (2000 ft), (2) 5 km", "(1) 600 m (2000 ft), (2) 8 km", "o Question 20 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "Altimeter temperature correction should be applied by the pilot during final approach, when the ambient temperature on the surface is much (1) ____ than ISA, and is (2) ____ minimum altitudes at fixes inside the Final Approach Fix (FAF). o (1) higher, (2) subtracted from (1) lower, (2) subtracted from",
                List.of("(1) higher, (2) added to", "(1) lower, (2) added to", "o Question 21 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "o During an LNAV 2D approach, (1) ___ is responsible to apply temperature corrections in case temperature is (2) ___ than ISA. o (1) ATC, (2) much higher (1) the pilot in command, (2) much lower",
                List.of("(1) ATC, (2) much lower", "(1) the pilot in command, (2) much higher", "o Question 22 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "According to ICAO Doc 8168 Volume I FLIGHT PROCEDURES PART III, when an IFR flight is being vectored by radar, air traffic control (ATC) may assign minimum radar vectoring altitudes which… o may be below the minimum sector altitude. must be at least 1000 ft above minimum sector altitude.",
                List.of("must be at the minimum sector altitude.", "may not be below the minimum sector altitude.", "o Question 23 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "Given the following information, calculate the minimum flight level to be flown under IFR: MOCA: 4400 ft o Magnetic course: 233(M) Elevation of nearby airport: 1500 ft Magnetic variation: 12°W Temperature: ISA-18 QNH reported at airport: 993 hPa Transition Altitude: 5000 feet o FL 70 FL 80",
                List.of("FL 60", "FL 50", "o Question 24 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "Given the following information, calculate the minimum flight level to be flown under IFR in RVSM airspace? Obstacle Altitude: 24800 ft Obstacle Clearance: 2000 ft Magnetic course: 330(M) Temperature: ISA-15 QNH reported at airport: 993 hPa o FL 310 FL 290",
                List.of("FL 300", "FL 280", "o Question 25 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "Given the following information, calculate the true altitude of the aircraft of the aircraft above airfield datum (“true height”): Aircraft at FL 45 Temperature: ISA-13 Elevation of nearby airport: 200 ft QNH at airport: 1030 hPa o 3560 ft 5040 ft",
                List.of("4580 ft", "4020 ft", "o Question 26 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See attachment IC-033-007) On departure, the waypoint EH087… o is reached after 7.5 NM from AMS. is a waypoint that must be flown-over.",
                List.of("requires a minimum altitude of 8500 ft.", "is a waypoint that requires turn-anticipation.", "o Question 27 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See attachment IC-033-096) The RNAV symbol in line D refers to a: o Fly-over waypoint Mileage break",
                List.of("o Non-compulsory waypoint", "Compulsory waypoint", "o Question 28 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See attachment IC-033-096) The RNAV symbol in line B refers to a: o Compulsory waypoint Fly-over waypoint",
                List.of("Non-compulsory waypoint", "Mileage break", "o Question 29 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See attachment IC-033-096) The RNAV symbol in line A refers to a: o Non-compulsory waypoint Compulsory waypoint",
                List.of("Fly-over waypoint", "Mileage break", "o Question 30 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See attachment IC-033-096) The RNAV symbol in line C refers to a: o Fly-by waypoint Mileage break",
                List.of("Compulsory waypoint", "Fly-over waypoint", "o Question 31 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See attachment IC-033-007) The route distance (NM) from runway 36L to ARNEM via the ARNEM 1Z departure route is: o 63.5 NM 68 NM",
                List.of("63 NM", "65.5 NM", "o Question 32 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See attachment IC-033-003) The correct ZURICH EAST departure route via D5.0 ZUE for Runway 32 is: o ZUE 3F ZUE 1G",
                List.of("ZUE 1M", "o ZUE 3L", "o Question 33 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See attachment IC-033-001) What is the initial approach fix (IAF) for an arrival via DOMUS to RWY 27R? o BANOX DOMUS",
                List.of("PGS", "PG516", "o Question 34 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See attachment IC-033-001) The Minimum Sector Altitude (MSA) based on the airport reference point is: o 6600 ft 3200 ft",
                List.of("4000 ft", "3300 ft", "o Question 35 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See attachment IC-033-001) Determine the Minimum Holding Altitude (MHA) and max. holding speed (IAS) at MOPAR holding: o FL070 and 250 KT FL110 and 280 KT",
                List.of("FL070 and 230 KT", "FL140 and 250 KT", "o Question 36 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See attachment IC-033-003) The correct departure via ZURICH EAST from runway 34 is: o ZUE 3L ZUE 3F",
                List.of("ZUE 1G", "ZUE 1M", "o Question 37 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See attachment IC-033-002) What is the elevation of runway 09R LFPG? o 392 ft 823 ft",
                List.of("371 ft", "3200 ft", "o Question 38 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-022) An aircraft proceeding on the EXOBI 3B SID is passing DME 15.0 JSV. What is the MSA at this point? o 7000 ft 8400 ft",
                List.of("o 8000 ft", "7800 ft", "o Question 39 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-022) An aircraft cleared for the EXOBI 1A SID for runway 03 L/R is permitted to climb initially to… o FL90. 7800 ft altitude.",
                List.of("FL75.", "8000 ft altitude.", "o Question 40 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See attachment IC-033-005) What is the correct arrival route when arriving via DISUN? o MERSI 4A KOGOL 4A",
                List.of("OSDER 4A", "IRBIR 5A", "o Question 41 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-027) All aircraft following the TETAN departures are required to cross the CTR boundary… o at or above 2,500 ft. with radio contact to the tower.",
                List.of("at or above FL85.", "with the altimeter set to standard.", "o Question 42 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-023) An aircraft is cleared to descent according the procedure following the arrival AXODO 1A. The first altitude change must be initiated at… o DUNBA, descending to FL95. DUNBA, descending to 9,500 ft altitude.",
                List.of("SUDOG, descending to FL150.", "DUNBA, descending to FL150.", "o Question 43 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-023) What is the outbound course of the AXODO holding? o 217° 300°",
                List.of("119°", "299°", "o Question 44 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-026) What is the total distance of the UVBIX 2C arrival? o 51 NM o 74 NM",
                List.of("72 NM", "48 NM", "o Question 45 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-026) What is the initial approach fix when following the UVBIX 2C arrival? o UVBIX OKPEX",
                List.of("UTIMO", "BONDY", "o Question 46 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-025) The MSA of FAOR is based on… o the airport reference point. JSV VOR.",
                List.of("the highest runway elevation.", "WKV VOR.", "o Question 47 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-025) What is the height of the highest terrain high point or man-made structure of the chart? o 8400 ft 7800 ft",
                List.of("6793 ft", "8900 ft", "o Question 48 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-025) An aircraft following the AVAGO 2A arrival is on JSV radial 320 inbound at a distance of 10.0 JSV DME. What is the current MSA for this aircraft? o 8900 ft 8400 ft",
                List.of("7800 ft", "8000 ft", "o Question 49 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-027) An aircraft cleared for the TETAN 1B SID for runway 19 is permitted to climb initially to… o 7500 ft altitude. FL75.",
                List.of("FL85.", "FL90.", "o Question 50 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-023) What is the inbound course of the ATUPI holding? o 299° 217°",
                List.of("037°", "036°", "o Question 51 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-023) What is the aproximate route distance of the ATUPI 1A arrival? o 64 NM 32 NM",
                List.of("28 NM", "35 NM", "o Question 52 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-023) The route distance of the AXODO 1A arrival is: o 58 NM 32 NM",
                List.of("64 NM", "60 NM", "o Question 53 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-023) What is the initial approach fix (IAF) of the ATUPI 1A arrival route? o UNDID OLLIE",
                List.of("ATUPI", "EGLIK", "o Question 54 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "Unless on Standard Instrument Departure charts, routes usually indicate… o True Heading. Magnetic Heading.",
                List.of("Magnetic Course.", "True Course.", "o Question 55 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "With regard to RNAV and conventional STARs, which of the following statements is correct? 1. “RNAV STAR overlay” charts provide RNAV information to support flying a conventional STAR. 2. An “RNAV STAR” must NOT be flown with reference to conventional sources only. o 1 is correct, 2 is correct 1 is correct, 2 is incorrect",
                List.of("1 is incorrect, 2 is correct", "1 is incorrect, 2 is incorrect", "o Question 56 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "With regard to RNAV and conventional SIDs, which of the following statements is correct? 1. A “RNAV SID” may be flown without any ground-based navigation aids. 2. A “RNAV SID” must be flown in case ground-based navigational navigation aids become unusable. o 1 is correct, 2 is incorrect 1 is incorrect, 2 is incorrect",
                List.of("1 is correct, 2 is correct", "1 is incorrect, 2 is correct", "o Question 57 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "With regard to RNAV and conventional SIDs, which of the following statements is correct? 1. A “RNAV STAR overlay” provides shortcuts compared to a STAR using ground-based navigational aids. 2. A “SID RNAV only” is limited to aircraft with approved RNAV equipment only. o 1 is incorrect, 2 is correct 1 is correct, 2 is correct",
                List.of("1 is correct, 2 is incorrect", "1 is incorrect, 2 is incorrect", "o Question 58 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "A commercial aeroplane is capable for an Approach Climb Gradient (ACG) of 3.5%. Instrument approach procedures that o 1. ILS-DME approach with required minimum ACG is 2.9% 2. VOR-DME approach minimum ACG required is 4.4% 3. LOC-DME approach with required minimum ACG required is 3.5% o 1 and 3. 1, 2 and 3",
                List.of("2 and 3", "1 and 2", "o Question 59 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See attachment IC-033-092) Select the (1) Frequency (MHz) and (2) QDM (deg.) of the ILS at De Kooy Aerodrome: o (1) 109.70 (2) 210°T (1) 123.30 (2) 220°T",
                List.of("(1) 109.70 (2) 216°M", "(1) 123.30 (2) 220°M", "o Question 60 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See attachment IC-033-006) What is the frequency of the NDB for the published NDB approach on runway 26L? o 115.0 MHz 400 kHz",
                List.of("108.3 MHz", "338 kHz", "o Question 61 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-004) In a Category A aircraft with all other aids serviceable and a missed approach climb gradient until 3,200 ft of 4.2%, the o 2100 m 2500 m",
                List.of("1500 m", "1200 m", "o Question 62 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See attachment IC-033-002) Determine the ILS localizer final approach course: o 134° 114°",
                List.of("085°", "110°", "o Question 63 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-002) What are the ILS frequency for RWY 09R? o 110.1 MHz 119.25 MHz",
                List.of("115.35 MHz", "127.13 MHz", "o Question 64 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See attachment IC-033-001) What is the crossing altitude overhead BANOX for a propeller aircraft? o FL 150 FL 140",
                List.of("FL 280", "FL 070", "o Question 65 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See attachment IC-033-002) The Minimum Descent Altitude (MDA) for an ILS glide slope out is: o 760 ft 389 ft",
                List.of("581 ft", "571 ft", "o Question 66 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See attachment IC-033-057) The Radio Altimeter minimum altitude for a CAT 2 ILS DME approach to Rwy 01L is: o 100 ft 300 ft",
                List.of("188 ft", "88 ft", "o Question 67 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See attachment IC-033-006) The minimum initial descent altitude for a an NDB approach on runway 26L is: o 5000 ft 4040 ft",
                List.of("2800 ft", "2330 ft", "o Question 68 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-004) The initial climb altitude during the missed approach procedure for runway 26 is: o 10300 ft 9500 ft",
                List.of("11600 ft", "11500 ft", "o Question 69 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See attachment IC-033-002) The Decision Altitude (DA) for an ILS straight-in landing with an aircraft category A is: o 581 ft 571 ft",
                List.of("760 ft", "200 ft", "o Question 70 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-021) What is the MSA for an aircraft approaching FBSK on a track of 310° for an ILS DME approach on runway 08? o 6400 ft 6100 ft",
                List.of("6000 ft", "7000 ft", "o Question 71 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-021) What is the minimum descent altitude for a localizer approach with NO vertical guidance (GS out)? o 3960 ft 4060 ft",
                List.of("3600 ft", "3492 ft", "o Question 72 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-020) What is the published descent angle for the RNAV approach on runway 01? o 10.0° 3.0°",
                List.of("5.0°", "3.8°", "o Question 73 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-019) What is the frequency of the localizer for the published ILS approach on runway 19? o 115.7 MHz 109.1 MHz",
                List.of("189.0 MHz", "o 462.5 kHz", "o Question 74 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-019) o What is the speed limit, when established on localizer at 10.0 KSI DME on the ILS approach for runway 19 in Cape Town? o As advised by ATC 160 kt",
                List.of("180 kt", "150 kt", "o Question 75 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-019) What speed restriction has to be considered during an ILS approach on runway 19 when passing the check altitude of 1973 ft? o No speed restriction Maximum 150 kt",
                List.of("Maximum 180 kt", "Maximum 130 kt", "o Question 76 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-021) Which radial must a category C aircraft intercept when passing GSV VOR for a standard ILS DME approach on runway 08? o R077 R275",
                List.of("R266", "R074", "o Question 77 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-018) What minimum navigation equipment is required for a VOR approach on runway 19 at Cape Town airport? o ADF and DME CDI and ADF",
                List.of("CDI only", "CDI and DME", "o Question 78 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-018) Determine the minimum RVR for a category A aircraft, assuming the approach light system is out of service: o 1800 m 1500 m",
                List.of("900 m", "1000 m", "o Question 79 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "o The lowest ICAO altitude providing minimum 300 m (1000 ft) clearance above all objects in a 46 km (25 NM) radius centred on a radio navigational aid, is the: o MSA MEA",
                List.of("MORA", "MOCA", "o Question 80 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-015) What is the elevation of runway 03R FAOR? o 5558 ft 5510 ft",
                List.of("7800 ft", "186 ft", "o Question 81 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-015) The Minimum Sector Altitude (MSA) is based on… o JNI ILS DME. HGV VOR.",
                List.of("the airport reference point.", "JSV VOR.", "o Question 82 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-015) The initial climb altitude during the missed approach procedure for runway 03R is: o 7800 ft 8400 ft",
                List.of("8000 ft", "9000 ft", "o Question 83 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-015) The ILS localizer course for runway 03R is… o 028°. 030°.",
                List.of("034°.", "214°.", "o Question 84 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-016) What is the minimum descent altitude for an NDB DME approach on runway 26? o 3960 ft 5500 ft",
                List.of("3650 ft", "373 ft", "o Question 85 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "o (See annex IC-033-016) What is the frequency of the NDB for the published NDB DME approach on runway 26? o 126.6 MHz 128 kHz",
                List.of("114.0 MHz", "311 kHz", "o Question 86 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-015) An aircraft is approaching FAOR for the ILS on runway 03R on a 250° track. What Minimum Sector Altitude has to be considered? o 9000 ft 6700 ft",
                List.of("7800 ft", "8400 ft", "o Question 87 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-016) The Missed Approach Point of the NDB DME approach for runway 26 is located at: o D1.1 GSV DME GBE NDB",
                List.of("D7.9 GSV DME", "D3.3 GSV DME", "o Question 88 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-016) The Missed Approach procedure requires a climb to an altitude of (1) ____ on a track of (2) ____ . o (1) 6000 ft, (2) 257° (1) 6400 ft, (2) 257°",
                List.of("(1) 6000 ft, (2) 270°", "(1) 6400 ft, (2) 270°", "o Question 89 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-017) Determine the Minimum Descent Height for a category C aircraft using lateral and vertical guidance. o 368 ft 1800 m",
                List.of("6010 ft", "1500 m", "o Question 90 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-017) What is the transition altitude when departing from Windhoek, Namibia? o 10000 ft As advised by ATC",
                List.of("5000 ft", "9000 ft", "o Question 91 of 122", "o ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-017) Under what conditions is a LNAV/VNAV approach based on barometric measurement NOT permitted? o Tailwind exceeds 10 kt ISA deviation is more than -15°C",
                List.of("Crosswind exceeds 25 kt", "OAT is below -15°C", "o Question 92 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-018) The frequency of Cape Town Director is: o 124.35 MHz 124.35 kHz",
                List.of("119.7 kHz", "119.7 MHz", "o Question 93 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See annex IC-033-016) What altitude indication is required at 4.0 GSV DME, in order to maintain the ideal descent path during the NDB DME approach for runway 26? o 5500 ft 3940 ft",
                List.of("6000 ft", "4260 ft", "o Question 94 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "The approach chart for an RNAV approach shows a decision height of 320 ft. Type and category of this approach are: o Type B, Category II Type A, no category",
                List.of("Type A, Category I", "Type B, no category", "o Question 95 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "With regard to PBN approaches designated as RNP APCH, which of the following statements is correct? o 1. RNP approaches are based on GNSS. 2. RNP approaches can be identified by LNAV or LNAV/VNAV minima published on the chart. o 1 is incorrect, 2 is correct 1 is correct, 2 is correct",
                List.of("1 is incorrect, 2 is incorrect", "1 is correct, 2 is incorrect", "o Question 96 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "Type B approaches have a decision height of (1) ____ and (2) ____ . o (1) less than 250 ft, (2) categories I, II or III (1) more than 250 ft, (2) categories I, II or III",
                List.of("(1) less than 250 ft, (2) no category", "(1) more than 250 ft, (2) no category", "o Question 97 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "Category and type of an ILS CAT I approach with decision height (DH) of 200 ft are: o Type A, 3D approach Type B, 2D approach",
                List.of("Type B, 3D approach", "Type A, 2D approach", "o Question 98 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "A RNP Type B approach must be 3D and requires: o 1. ILS 2. MLS 3. GBAS 4. SBAS 5. ABAS only o 1, 2 or 5 3, 4 or 5",
                List.of("1, 2, 3 or 4", "1, 3, 4 or 5", "o Question 99 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "The approach chart for an approach shows a decision height of 150 ft. Type and category of this approach are: o Type B, Category II Type A, no category",
                List.of("Type B, no category", "Type A, Category I", "o Question 100 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "With regard to definition by ICAO Annex 2, a non-precision approach is an instrument approach procedure designed for (1) ____ instrument approach operations of (2) ____ . o (1) 2D, (2) Type A (1) 2D, (2) Type B",
                List.of("(1) 3D, (2) Type A", "(1) 3D, (2) Type B", "o Question 101 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "With regard to LPV approaches, the (1) ____ guidance is equivalent to localizer, and the protected area is considerably (2) ____ than the protected area for the present LNAV and LNAV/VNAV lateral protection. o (1) lateral, (2) larger (1) lateral, (2) smaller",
                List.of("(1) vertical, (2) larger", "(1) vertical, (2) smaller", "o Question 102 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "A non-precision approach in its most basic form uses reference from (1) ____ only and provides (2) ____ . o (2) GNSS waypoints, (2) lateral and vertical guidance (1) ground-based facilities, (2) lateral and vertcial guidance",
                List.of("o (1) ground-based facilities, (2) lateral guidance only by VOR or Localizer", "(2) GNSS waypoints, (2) lateral guidance only", "o Question 103 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "An example for an RNP approach procedure which must be designated as “RNP AR APCH” may be… o any published LNAV/VNAV minima. a curved segment in the final approach.",
                List.of("any published LPV minima.", "a curved segment in the arrival path.", "o Question 104 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "According ICAO Doc 9613 regarding approaches designated as “RNP AR APCH”, the State AIP should clearly indicate that the navigation application is an RNP AR APCH procedure and that… o alternative routings are required. augmented reality features are required.",
                List.of("specific authorization is required.", "special equipment is required.", "o Question 105 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "A LPV provides precision approaches (1) ____ vertical guidance down to minima as low as (2) ____ . o (1) without, (2) 100 ft (1) with, (2) 100 ft",
                List.of("(1) without, (2) 200 ft", "(1) with, (2) 200 ft", "o Question 106 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "A LPV approach is… o an augmented GNSS approach based on localizer performance with vertical guidance. a limited-precision approach based on ground-based facilities without vertical guidance.",
                List.of("an augmented GNSS approach based on localizer performance without vertical guidance.", "a limited-precision approach based on ground-based facilities with vertical guidance.", "o Question 107 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "In case an RNP approach chart shows two types of published minima for LNAV and LP, the (1) ____ are based on Localiser Performance requiring (2) ____ on board the aircraft. o (1) LP minima, (2) additional equipment to standard GNSS receiver (1) LP minima, (2) standard GNSS equipment",
                List.of("(1) LNAV minima, (2) additional equipment to standard GNSS receiver", "(1) LNAV minima, (2) standard GNSS equipment", "o Question 108 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "In case of FMS inability to provide vertical guidance, a RNP approach may be flown… o as a non-precision approach with vertical guidance by on-board systems. as a precision approach with vertical guidance by on-board systems.",
                List.of("as a precision approach without vertical guidance by on-board systems.", "as a non-precision approach without vertical guidance by on-board systems.", "o Question 109 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "An approach designated as “APV Baro” or “APV SBAS” is a (1) ____ down to minima published as (2) ____ . o (1) 2D approach, (2) LNAV/VNAV or LPV (1) 3D approach, (2) LNAV/VNAV or LPV",
                List.of("(1) 3D approach, (2) MDH or DH", "(1) 2D approach, (2) LNAV or LP", "o Question 110 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "(See attachment IC-033-034) In case you accidently change the COM-Frequence while approaching ODIN (N55 34.8 E010 39.3) via airway UA4, what frequency should you select to re-establish communication? o 126.05 MHz 128.15 MHz",
                List.of("119.55 MHz or 128.75 MHz", "115.5 MHz", "o Question 111 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null,
                "Cruise Level FL370 Cruise Regime M 0.74 OAT -47° C The TAS in kt is: o 438 424",
                List.of("416", "434", "o Question 112 of 122", "ID:"),
                0,
                instrumentRating
            ),
            new Question(
                null, // No id provided in this part
                "True course 130° W/V 180/30 TAS 200 kt Calculate the Wind Correction...(truncated 10289 characters)...erance area is correct? o 1. The dimension of the fix tolerance area is based on ground system tolerance 2. The dimension of the fix tolerance area is based on airborne receiving system tolerance. o 1 is correct, 2 is correct 1 is incorrect, 2 is correct",
                List.of("1 is correct, 2 is incorrect", "1 is incorrect, 2 is incorrect", "o Question 2 of 40", "ID:"),
                0,
                instrumentRating
            )
            // Note: The JSON is truncated and mixed with other sections. Add remaining questions similarly if full JSON is available.
            // For brevity, I've included all explicitly listed questions from the provided text. The last few are from mixed sections and may need cleaning.
            // If more are needed, extend the list accordingly.
        );
    }
}
