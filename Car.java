import java.util.Iterator;
import java.lang.Iterable;

public class Car {

    public static enum Attribute {
        CYLINDERS,
        DISPLACEMENT,
        HORSEPOWER,
        ACCELERATION,
        WEIGHT,
        YEAR;

        public static final int count = Attribute.values().length;
    }

    private double  mpg;
    private int     cylinders;
    private double  displacement;
    private double  horsepower;
    private double  acceleration;
    private double  weight;
    private int     year;
    private String  name;

    private Car(
        double  mpg,
        int     cylinders,
        double  displacement,
        double  horsepower,
        double  weight,
        double  acceleration,
        int     year,
        String name) {

        this.mpg = mpg;
        this.cylinders = cylinders;
        this.displacement = displacement;
        this.horsepower = horsepower;
        this.acceleration = acceleration;
        this.weight = weight;
        this.year = year;
        this.name = name;
    }

    public Car(
            int     cylinders,
            double  displacement,
            double  horsepower,
            double  weight,
            double  acceleration,
            int     year,
            String name) {

        this.mpg = -1;
        this.cylinders = cylinders;
        this.displacement = displacement;
        this.horsepower = horsepower;
        this.acceleration = acceleration;
        this.weight = weight;
        this.year = year;
        this.name = name;
    }

    public double mpg()          { return this.mpg; };
    public boolean efficient()   { return this.mpg >= 25.0; }
    public int cylinders()       { return this.cylinders; }
    public double displacement() { return this.displacement; }
    public double horsepower()   { return this.horsepower; }
    public double weight()       { return this.weight; }
    public double acceleration() { return this.acceleration; }
    public int year()            { return this.year; }
    public String name()         { return this.name; }

    public double get(Attribute attribute) {
        switch(attribute) {
            case CYLINDERS:    return this.cylinders();
            case DISPLACEMENT: return this.displacement();
            case HORSEPOWER:   return this.horsepower();
            case ACCELERATION: return this.acceleration();
            case WEIGHT:       return this.weight();
            case YEAR:         return this.year();
        }
        return NA;
    }

    public Vector getAttributes() {
        Vector result = new Vector(Attribute.count);
        for (Attribute attribute : Attribute.values()) {
            result.set(attribute.ordinal(), this.get(attribute));
        }
        return result;
    }

    public Vector getNormalizedAttributes() {
        Vector result = new Vector(Attribute.count);
        for (Attribute attribute : Attribute.values()) {
            result.set(attribute.ordinal(), (this.get(attribute) - minValue(attribute)) / (maxValue(attribute) - minValue(attribute)));
        }
        return result;
    }

    public double minValue(Attribute attribute) {
        switch(attribute) {
            case CYLINDERS:    return 4;
            case DISPLACEMENT: return 0;
            case HORSEPOWER:   return 46;
            case ACCELERATION: return 8;
            case WEIGHT:       return 1613;
            case YEAR:         return 1970;
        }
        return NA;

    }

    public double maxValue(Attribute attribute) {
        switch(attribute) {
            case CYLINDERS:    return 8;
            case DISPLACEMENT: return 455;
            case HORSEPOWER:   return 230;
            case ACCELERATION: return 24.8;
            case WEIGHT:       return 5140;
            case YEAR:         return 1982;
        }
        return NA;
    }

    public double normalizedMPG() {
        return (this.mpg - 9)/(46.6 - 9);
    }

    private static class CarIterator implements Iterator<Car> {
    
		private Car[] data;
        private int index;

        public CarIterator(Car[] data) {
			this.data = data;
            this.index = 0;
        }

        public boolean hasNext() {
            return this.index < data.length;
        }

        public Car next() {
            return this.data[this.index++];
        }
    }

    public static final Iterable<Car> cars = new Iterable<Car>() {
        @Override
        public Iterator<Car> iterator() {
            return new CarIterator(Car.data);
        }
    };

	public static final Iterable<Car> trainingCars = new Iterable<Car>() {
        @Override
        public Iterator<Car> iterator() {
            return new CarIterator(Car.trainingData);
        }
	};

	public static final Iterable<Car> testingCars = new Iterable<Car>() {
        @Override
        public Iterator<Car> iterator() {
            return new CarIterator(Car.testingData);
        }
	};


    public static final int NA = -1;

    public static Car[] data = {
        new Car(18.0, 8, 307.0, 130.0, 3504.0, 12.0, 1970, "chevrolet chevelle malibu"),
        new Car(15.0, 8, 350.0, 165.0, 3693.0, 11.5, 1970, "buick skylark 320"),
        new Car(18.0, 8, 318.0, 150.0, 3436.0, 11.0, 1970, "plymouth satellite"),
        new Car(16.0, 8, 304.0, 150.0, 3433.0, 12.0, 1970, "amc rebel sst"),
        new Car(17.0, 8, 302.0, 140.0, 3449.0, 10.5, 1970, "ford torino"),
        new Car(15.0, 8, 429.0, 198.0, 4341.0, 10.0, 1970, "ford galaxie 500"),
        new Car(14.0, 8, 454.0, 220.0, 4354.0,  9.0, 1970, "chevrolet impala"),
        new Car(14.0, 8, 440.0, 215.0, 4312.0,  8.5, 1970, "plymouth fury iii"),
        new Car(14.0, 8, 455.0, 225.0, 4425.0, 10.0, 1970, "pontiac catalina"),
        new Car(15.0, 8, 390.0, 190.0, 3850.0,  8.5, 1970, "amc ambassador dpl"),
        new Car(15.0, 8, 383.0, 170.0, 3563.0, 10.0, 1970, "dodge challenger se"),
        new Car(14.0, 8, 340.0, 160.0, 3609.0,  8.0, 1970, "plymouth 'cuda 340"),
        new Car(15.0, 8, 400.0, 150.0, 3761.0,  9.5, 1970, "chevrolet monte carlo"),
        new Car(14.0, 8, 455.0, 225.0, 3086.0, 10.0, 1970, "buick estate wagon (sw)"),
        new Car(24.0, 4, 113.0, 95.00, 2372.0, 15.0, 1970, "toyota corona mark ii"),
        new Car(22.0, 6, 198.0, 95.00, 2833.0, 15.5, 1970, "plymouth duster"),
        new Car(18.0, 6, 199.0, 97.00, 2774.0, 15.5, 1970, "amc hornet"),
        new Car(21.0, 6, 200.0, 85.00, 2587.0, 16.0, 1970, "ford maverick"),
        new Car(27.0, 4, 97.00, 88.00, 2130.0, 14.5, 1970, "datsun pl510"),
        new Car(26.0, 4, 97.00, 46.00, 1835.0, 20.5, 1970, "volkswagen 1131 deluxe sedan"),
        new Car(25.0, 4, 110.0, 87.00, 2672.0, 17.5, 1970, "peugeot 504"),
        new Car(24.0, 4, 107.0, 90.00, 2430.0, 14.5, 1970, "audi 100 ls"),
        new Car(25.0, 4, 104.0, 95.00, 2375.0, 17.5, 1970, "saab 99e"),
        new Car(26.0, 4, 121.0, 113.0, 2234.0, 12.5, 1970, "bmw 2002"),
        new Car(21.0, 6, 199.0, 90.00, 2648.0, 15.0, 1970, "amc gremlin"),
        new Car(10.0, 8, 360.0, 215.0, 4615.0, 14.0, 1970, "ford f250"),
        new Car(10.0, 8, 307.0, 200.0, 4376.0, 15.0, 1970, "chevy c20"),
        new Car(11.0, 8, 318.0, 210.0, 4382.0, 13.5, 1970, "dodge d200"),
        new Car( 9.0, 8, 304.0, 193.0, 4732.0, 18.5, 1970, "hi 1200d"),
        new Car(27.0, 4, 97.00, 88.00, 2130.0, 14.5, 1971, "datsun pl510"),
        new Car(28.0, 4, 140.0, 90.00, 2264.0, 15.5, 1971, "chevrolet vega 2300"),
        new Car(25.0, 4, 113.0, 95.00, 2228.0, 14.0, 1971, "toyota corona"),
        new Car(25.0, 4, 98.00,    NA, 2046.0, 19.0, 1971, "ford pinto"),
        new Car(19.0, 6, 232.0, 100.0, 2634.0, 13.0, 1971, "amc gremlin"),
        new Car(16.0, 6, 225.0, 105.0, 3439.0, 15.5, 1971, "plymouth satellite custom"),
        new Car(17.0, 6, 250.0, 100.0, 3329.0, 15.5, 1971, "chevrolet chevelle malibu"),
        new Car(19.0, 6, 250.0, 88.00, 3302.0, 15.5, 1971, "ford torino 500"),
        new Car(18.0, 6, 232.0, 100.0, 3288.0, 15.5, 1971, "amc matador"),
        new Car(14.0, 8, 350.0, 165.0, 4209.0, 12.0, 1971, "chevrolet impala"),
        new Car(14.0, 8, 400.0, 175.0, 4464.0, 11.5, 1971, "pontiac catalina brougham"),
        new Car(14.0, 8, 351.0, 153.0, 4154.0, 13.5, 1971, "ford galaxie 500"),
        new Car(14.0, 8, 318.0, 150.0, 4096.0, 13.0, 1971, "plymouth fury iii"),
        new Car(12.0, 8, 383.0, 180.0, 4955.0, 11.5, 1971, "dodge monaco (sw)"),
        new Car(13.0, 8, 400.0, 170.0, 4746.0, 12.0, 1971, "ford country squire (sw)"),
        new Car(13.0, 8, 400.0, 175.0, 5140.0, 12.0, 1971, "pontiac safari (sw)"),
        new Car(18.0, 6, 258.0, 110.0, 2962.0, 13.5, 1971, "amc hornet sportabout (sw)"),
        new Car(22.0, 4, 140.0, 72.00, 2408.0, 19.0, 1971, "chevrolet vega (sw)"),
        new Car(19.0, 6, 250.0, 100.0, 3282.0, 15.0, 1971, "pontiac firebird"),
        new Car(18.0, 6, 250.0, 88.00, 3139.0, 14.5, 1971, "ford mustang"),
        new Car(23.0, 4, 122.0, 86.00, 2220.0, 14.0, 1971, "mercury capri 2000"),
        new Car(28.0, 4, 116.0, 90.00, 2123.0, 14.0, 1971, "opel 1900"),
        new Car(30.0, 4, 79.00, 70.00, 2074.0, 19.5, 1971, "peugeot 304"),
        new Car(30.0, 4, 88.00, 76.00, 2065.0, 14.5, 1971, "fiat 124b"),
        new Car(31.0, 4, 71.00, 65.00, 1773.0, 19.0, 1971, "toyota corolla 1200"),
        new Car(35.0, 4, 72.00, 69.00, 1613.0, 18.0, 1971, "datsun 1200"),
        new Car(27.0, 4, 97.00, 60.00, 1834.0, 19.0, 1971, "volkswagen model 111"),
        new Car(26.0, 4, 91.00, 70.00, 1955.0, 20.5, 1971, "plymouth cricket"),
        new Car(24.0, 4, 113.0, 95.00, 2278.0, 15.5, 1972, "toyota corona hardtop"),
        new Car(25.0, 4, 97.50, 80.00, 2126.0, 17.0, 1972, "dodge colt hardtop"),
        new Car(23.0, 4, 97.00, 54.00, 2254.0, 23.5, 1972, "volkswagen type 3"),
        new Car(20.0, 4, 140.0, 90.00, 2408.0, 19.5, 1972, "chevrolet vega"),
        new Car(21.0, 4, 122.0, 86.00, 2226.0, 16.5, 1972, "ford pinto runabout"),
        new Car(13.0, 8, 350.0, 165.0, 4274.0, 12.0, 1972, "chevrolet impala"),
        new Car(14.0, 8, 400.0, 175.0, 4385.0, 12.0, 1972, "pontiac catalina"),
        new Car(15.0, 8, 318.0, 150.0, 4135.0, 13.5, 1972, "plymouth fury iii"),
        new Car(14.0, 8, 351.0, 153.0, 4129.0, 13.0, 1972, "ford galaxie 500"),
        new Car(17.0, 8, 304.0, 150.0, 3672.0, 11.5, 1972, "amc ambassador sst"),
        new Car(11.0, 8, 429.0, 208.0, 4633.0, 11.0, 1972, "mercury marquis"),
        new Car(13.0, 8, 350.0, 155.0, 4502.0, 13.5, 1972, "buick lesabre custom"),
        new Car(12.0, 8, 350.0, 160.0, 4456.0, 13.5, 1972, "oldsmobile delta 88 royale"),
        new Car(13.0, 8, 400.0, 190.0, 4422.0, 12.5, 1972, "chrysler newport royal"),
        new Car(19.0, 7,  0.00, 97.00, 2330.0, 13.5, 1972, "mazda rx2 coupe"),
        new Car(15.0, 8, 304.0, 150.0, 3892.0, 12.5, 1972, "amc matador (sw)"),
        new Car(13.0, 8, 307.0, 130.0, 4098.0, 14.0, 1972, "chevrolet chevelle concours (sw)"),
        new Car(13.0, 8, 302.0, 140.0, 4294.0, 16.0, 1972, "ford gran torino (sw)"),
        new Car(14.0, 8, 318.0, 150.0, 4077.0, 14.0, 1972, "plymouth satellite custom (sw)"),
        new Car(18.0, 4, 121.0, 112.0, 2933.0, 14.5, 1972, "volvo 145e (sw)"),
        new Car(22.0, 4, 121.0, 76.00, 2511.0, 18.0, 1972, "volkswagen 411 (sw)"),
        new Car(21.0, 4, 120.0, 87.00, 2979.0, 19.5, 1972, "peugeot 504 (sw)"),
        new Car(26.0, 4, 96.00, 69.00, 2189.0, 18.0, 1972, "renault 12 (sw)"),
        new Car(22.0, 4, 122.0, 86.00, 2395.0, 16.0, 1972, "ford pinto (sw)"),
        new Car(28.0, 4, 97.00, 92.00, 2288.0, 17.0, 1972, "datsun 510 (sw)"),
        new Car(23.0, 4, 120.0, 97.00, 2506.0, 14.5, 1972, "toyouta corona mark ii (sw)"),
        new Car(28.0, 4, 98.00, 80.00, 2164.0, 15.0, 1972, "dodge colt (sw)"),
        new Car(27.0, 4, 97.00, 88.00, 2100.0, 16.5, 1972, "toyota corolla 1600 (sw)"),
        new Car(13.0, 8, 350.0, 175.0, 4100.0, 13.0, 1973, "buick century 350"),
        new Car(14.0, 8, 304.0, 150.0, 3672.0, 11.5, 1973, "amc matador"),
        new Car(13.0, 8, 350.0, 145.0, 3988.0, 13.0, 1973, "chevrolet malibu"),
        new Car(14.0, 8, 302.0, 137.0, 4042.0, 14.5, 1973, "ford gran torino"),
        new Car(15.0, 8, 318.0, 150.0, 3777.0, 12.5, 1973, "dodge coronet custom"),
        new Car(12.0, 8, 429.0, 198.0, 4952.0, 11.5, 1973, "mercury marquis brougham"),
        new Car(13.0, 8, 400.0, 150.0, 4464.0, 12.0, 1973, "chevrolet caprice classic"),
        new Car(13.0, 8, 351.0, 158.0, 4363.0, 13.0, 1973, "ford ltd"),
        new Car(14.0, 8, 318.0, 150.0, 4237.0, 14.5, 1973, "plymouth fury gran sedan"),
        new Car(13.0, 8, 440.0, 215.0, 4735.0, 11.0, 1973, "chrysler new yorker brougham"),
        new Car(12.0, 8, 455.0, 225.0, 4951.0, 11.0, 1973, "buick electra 225 custom"),
        new Car(13.0, 8, 360.0, 175.0, 3821.0, 11.0, 1973, "amc ambassador brougham"),
        new Car(18.0, 6, 225.0, 105.0, 3121.0, 16.5, 1973, "plymouth valiant"),
        new Car(16.0, 6, 250.0, 100.0, 3278.0, 18.0, 1973, "chevrolet nova custom"),
        new Car(18.0, 6, 232.0, 100.0, 2945.0, 16.0, 1973, "amc hornet"),
        new Car(18.0, 6, 250.0, 88.00, 3021.0, 16.5, 1973, "ford maverick"),
        new Car(23.0, 6, 198.0, 95.00, 2904.0, 16.0, 1973, "plymouth duster"),
        new Car(26.0, 4, 97.00, 46.00, 1950.0, 21.0, 1973, "volkswagen super beetle"),
        new Car(11.0, 8, 400.0, 150.0, 4997.0, 14.0, 1973, "chevrolet impala"),
        new Car(12.0, 8, 400.0, 167.0, 4906.0, 12.5, 1973, "ford country"),
        new Car(13.0, 8, 360.0, 170.0, 4654.0, 13.0, 1973, "plymouth custom suburb"),
        new Car(12.0, 8, 350.0, 180.0, 4499.0, 12.5, 1973, "oldsmobile vista cruiser"),
        new Car(18.0, 6, 232.0, 100.0, 2789.0, 15.0, 1973, "amc gremlin"),
        new Car(20.0, 4, 97.00, 88.00, 2279.0, 19.0, 1973, "toyota carina"),
        new Car(21.0, 4, 140.0, 72.00, 2401.0, 19.5, 1973, "chevrolet vega"),
        new Car(22.0, 4, 108.0, 94.00, 2379.0, 16.5, 1973, "datsun 610"),
        new Car(18.0, 7,  0.00, 90.00, 2124.0, 13.5, 1973, "maxda rx3"),
        new Car(19.0, 4, 122.0, 85.00, 2310.0, 18.5, 1973, "ford pinto"),
        new Car(21.0, 6, 155.0, 107.0, 2472.0, 14.0, 1973, "mercury capri v6"),
        new Car(26.0, 4, 98.00, 90.00, 2265.0, 15.5, 1973, "fiat 124 sport coupe"),
        new Car(15.0, 8, 350.0, 145.0, 4082.0, 13.0, 1973, "chevrolet monte carlo s"),
        new Car(16.0, 8, 400.0, 230.0, 4278.0,  9.5, 1973, "pontiac grand prix"),
        new Car(29.0, 4, 68.00, 49.00, 1867.0, 19.5, 1973, "fiat 128"),
        new Car(24.0, 4, 116.0, 75.00, 2158.0, 15.5, 1973, "opel manta"),
        new Car(20.0, 4, 114.0, 91.00, 2582.0, 14.0, 1973, "audi 100ls"),
        new Car(19.0, 4, 121.0, 112.0, 2868.0, 15.5, 1973, "volvo 144ea"),
        new Car(15.0, 8, 318.0, 150.0, 3399.0, 11.0, 1973, "dodge dart custom"),
        new Car(24.0, 4, 121.0, 110.0, 2660.0, 14.0, 1973, "saab 99le"),
        new Car(20.0, 6, 156.0, 122.0, 2807.0, 13.5, 1973, "toyota mark ii"),
        new Car(11.0, 8, 350.0, 180.0, 3664.0, 11.0, 1973, "oldsmobile omega"),
        new Car(20.0, 6, 198.0, 95.00, 3102.0, 16.5, 1974, "plymouth duster"),
        new Car(21.0, 6, 200.0,    NA, 2875.0, 17.0, 1974, "ford maverick"),
        new Car(19.0, 6, 232.0, 100.0, 2901.0, 16.0, 1974, "amc hornet"),
        new Car(15.0, 6, 250.0, 100.0, 3336.0, 17.0, 1974, "chevrolet nova"),
        new Car(31.0, 4, 79.00, 67.00, 1950.0, 19.0, 1974, "datsun b210"),
        new Car(26.0, 4, 122.0, 80.00, 2451.0, 16.5, 1974, "ford pinto"),
        new Car(32.0, 4, 71.00, 65.00, 1836.0, 21.0, 1974, "toyota corolla 1200"),
        new Car(25.0, 4, 140.0, 75.00, 2542.0, 17.0, 1974, "chevrolet vega"),
        new Car(16.0, 6, 250.0, 100.0, 3781.0, 17.0, 1974, "chevrolet chevelle malibu classic"),
        new Car(16.0, 6, 258.0, 110.0, 3632.0, 18.0, 1974, "amc matador"),
        new Car(18.0, 6, 225.0, 105.0, 3613.0, 16.5, 1974, "plymouth satellite sebring"),
        new Car(16.0, 8, 302.0, 140.0, 4141.0, 14.0, 1974, "ford gran torino"),
        new Car(13.0, 8, 350.0, 150.0, 4699.0, 14.5, 1974, "buick century luxus (sw)"),
        new Car(14.0, 8, 318.0, 150.0, 4457.0, 13.5, 1974, "dodge coronet custom (sw)"),
        new Car(14.0, 8, 302.0, 140.0, 4638.0, 16.0, 1974, "ford gran torino (sw)"),
        new Car(14.0, 8, 304.0, 150.0, 4257.0, 15.5, 1974, "amc matador (sw)"),
        new Car(29.0, 4, 98.00, 83.00, 2219.0, 16.5, 1974, "audi fox"),
        new Car(26.0, 4, 79.00, 67.00, 1963.0, 15.5, 1974, "volkswagen dasher"),
        new Car(26.0, 4, 97.00, 78.00, 2300.0, 14.5, 1974, "opel manta"),
        new Car(31.0, 4, 76.00, 52.00, 1649.0, 16.5, 1974, "toyota corona"),
        new Car(32.0, 4, 83.00, 61.00, 2003.0, 19.0, 1974, "datsun 710"),
        new Car(28.0, 4, 90.00, 75.00, 2125.0, 14.5, 1974, "dodge colt"),
        new Car(24.0, 4, 90.00, 75.00, 2108.0, 15.5, 1974, "fiat 128"),
        new Car(26.0, 4, 116.0, 75.00, 2246.0, 14.0, 1974, "fiat 124 tc"),
        new Car(24.0, 4, 120.0, 97.00, 2489.0, 15.0, 1974, "honda civic"),
        new Car(26.0, 4, 108.0, 93.00, 2391.0, 15.5, 1974, "subaru"),
        new Car(31.0, 4, 79.00, 67.00, 2000.0, 16.0, 1974, "fiat x1.9"),
        new Car(19.0, 6, 225.0, 95.00, 3264.0, 16.0, 1975, "plymouth valiant custom"),
        new Car(18.0, 6, 250.0, 105.0, 3459.0, 16.0, 1975, "chevrolet nova"),
        new Car(15.0, 6, 250.0, 72.00, 3432.0, 21.0, 1975, "mercury monarch"),
        new Car(15.0, 6, 250.0, 72.00, 3158.0, 19.5, 1975, "ford maverick"),
        new Car(16.0, 8, 400.0, 170.0, 4668.0, 11.5, 1975, "pontiac catalina"),
        new Car(15.0, 8, 350.0, 145.0, 4440.0, 14.0, 1975, "chevrolet bel air"),
        new Car(16.0, 8, 318.0, 150.0, 4498.0, 14.5, 1975, "plymouth grand fury"),
        new Car(14.0, 8, 351.0, 148.0, 4657.0, 13.5, 1975, "ford ltd"),
        new Car(17.0, 6, 231.0, 110.0, 3907.0, 21.0, 1975, "buick century"),
        new Car(16.0, 6, 250.0, 105.0, 3897.0, 18.5, 1975, "chevroelt chevelle malibu"),
        new Car(15.0, 6, 258.0, 110.0, 3730.0, 19.0, 1975, "amc matador"),
        new Car(18.0, 6, 225.0, 95.00, 3785.0, 19.0, 1975, "plymouth fury"),
        new Car(21.0, 6, 231.0, 110.0, 3039.0, 15.0, 1975, "buick skyhawk"),
        new Car(20.0, 8, 262.0, 110.0, 3221.0, 13.5, 1975, "chevrolet monza 2+2"),
        new Car(13.0, 8, 302.0, 129.0, 3169.0, 12.0, 1975, "ford mustang ii"),
        new Car(29.0, 4, 97.00, 75.00, 2171.0, 16.0, 1975, "toyota corolla"),
        new Car(23.0, 4, 140.0, 83.00, 2639.0, 17.0, 1975, "ford pinto"),
        new Car(20.0, 6, 232.0, 100.0, 2914.0, 16.0, 1975, "amc gremlin"),
        new Car(23.0, 4, 140.0, 78.00, 2592.0, 18.5, 1975, "pontiac astro"),
        new Car(24.0, 4, 134.0, 96.00, 2702.0, 13.5, 1975, "toyota corona"),
        new Car(25.0, 4, 90.00, 71.00, 2223.0, 16.5, 1975, "volkswagen dasher"),
        new Car(24.0, 4, 119.0, 97.00, 2545.0, 17.0, 1975, "datsun 710"),
        new Car(18.0, 6, 171.0, 97.00, 2984.0, 14.5, 1975, "ford pinto"),
        new Car(29.0, 4, 90.00, 70.00, 1937.0, 14.0, 1975, "volkswagen rabbit"),
        new Car(19.0, 6, 232.0, 90.00, 3211.0, 17.0, 1975, "amc pacer"),
        new Car(23.0, 4, 115.0, 95.00, 2694.0, 15.0, 1975, "audi 100ls"),
        new Car(23.0, 4, 120.0, 88.00, 2957.0, 17.0, 1975, "peugeot 504"),
        new Car(22.0, 4, 121.0, 98.00, 2945.0, 14.5, 1975, "volvo 244dl"),
        new Car(25.0, 4, 121.0, 115.0, 2671.0, 13.5, 1975, "saab 99le"),
        new Car(33.0, 4, 91.00, 53.00, 1795.0, 17.5, 1975, "honda civic cvcc"),
        new Car(28.0, 4, 107.0, 86.00, 2464.0, 15.5, 1976, "fiat 131"),
        new Car(25.0, 4, 116.0, 81.00, 2220.0, 16.9, 1976, "opel 1900"),
        new Car(25.0, 4, 140.0, 92.00, 2572.0, 14.9, 1976, "capri ii"),
        new Car(26.0, 4, 98.00, 79.00, 2255.0, 17.7, 1976, "dodge colt"),
        new Car(27.0, 4, 101.0, 83.00, 2202.0, 15.3, 1976, "renault 12tl"),
        new Car(17.5, 8, 305.0, 140.0, 4215.0, 13.0, 1976, "chevrolet chevelle malibu classic"),
        new Car(16.0, 8, 318.0, 150.0, 4190.0, 13.0, 1976, "dodge coronet brougham"),
        new Car(15.5, 8, 304.0, 120.0, 3962.0, 13.9, 1976, "amc matador"),
        new Car(14.5, 8, 351.0, 152.0, 4215.0, 12.8, 1976, "ford gran torino"),
        new Car(22.0, 6, 225.0, 100.0, 3233.0, 15.4, 1976, "plymouth valiant"),
        new Car(22.0, 6, 250.0, 105.0, 3353.0, 14.5, 1976, "chevrolet nova"),
        new Car(24.0, 6, 200.0, 81.00, 3012.0, 17.6, 1976, "ford maverick"),
        new Car(22.5, 6, 232.0, 90.00, 3085.0, 17.6, 1976, "amc hornet"),
        new Car(29.0, 4, 85.00, 52.00, 2035.0, 22.2, 1976, "chevrolet chevette"),
        new Car(24.5, 4, 98.00, 60.00, 2164.0, 22.1, 1976, "chevrolet woody"),
        new Car(29.0, 4, 90.00, 70.00, 1937.0, 14.2, 1976, "vw rabbit"),
        new Car(33.0, 4, 91.00, 53.00, 1795.0, 17.4, 1976, "honda civic"),
        new Car(20.0, 6, 225.0, 100.0, 3651.0, 17.7, 1976, "dodge aspen se"),
        new Car(18.0, 6, 250.0, 78.00, 3574.0, 21.0, 1976, "ford granada ghia"),
        new Car(18.5, 6, 250.0, 110.0, 3645.0, 16.2, 1976, "pontiac ventura sj"),
        new Car(17.5, 6, 258.0, 95.00, 3193.0, 17.8, 1976, "amc pacer d/l"),
        new Car( 9.5, 4, 97.00, 71.00, 1825.0, 12.2, 1976, "volkswagen rabbit"),
        new Car(32.0, 4, 85.00, 70.00, 1990.0, 17.0, 1976, "datsun b-210"),
        new Car(28.0, 4, 97.00, 75.00, 2155.0, 16.4, 1976, "toyota corolla"),
        new Car(26.5, 4, 140.0, 72.00, 2565.0, 13.6, 1976, "ford pinto"),
        new Car(20.0, 4, 130.0, 102.0, 3150.0, 15.7, 1976, "volvo 245"),
        new Car(13.0, 8, 318.0, 150.0, 3940.0, 13.2, 1976, "plymouth volare premier v8"),
        new Car(19.0, 4, 120.0, 88.00, 3270.0, 21.9, 1976, "peugeot 504"),
        new Car(19.0, 6, 156.0, 108.0, 2930.0, 15.5, 1976, "toyota mark ii"),
        new Car(16.5, 6, 168.0, 120.0, 3820.0, 16.7, 1976, "mercedes-benz 280s"),
        new Car(16.5, 8, 350.0, 180.0, 4380.0, 12.1, 1976, "cadillac seville"),
        new Car(13.0, 8, 350.0, 145.0, 4055.0, 12.0, 1976, "chevy c10"),
        new Car(13.0, 8, 302.0, 130.0, 3870.0, 15.0, 1976, "ford f108"),
        new Car(13.0, 8, 318.0, 150.0, 3755.0, 14.0, 1976, "dodge d100"),
        new Car(31.5, 4, 98.00, 68.00, 2045.0, 18.5, 1977, "honda accord cvcc"),
        new Car(30.0, 4, 111.0, 80.00, 2155.0, 14.8, 1977, "buick opel isuzu deluxe"),
        new Car(36.0, 4, 79.00, 58.00, 1825.0, 18.6, 1977, "renault 5 gtl"),
        new Car(25.5, 4, 122.0, 96.00, 2300.0, 15.5, 1977, "plymouth arrow gs"),
        new Car(33.5, 4, 85.00, 70.00, 1945.0, 16.8, 1977, "datsun f-10 hatchback"),
        new Car(17.5, 8, 305.0, 145.0, 3880.0, 12.5, 1977, "chevrolet caprice classic"),
        new Car(17.0, 8, 260.0, 110.0, 4060.0, 19.0, 1977, "oldsmobile cutlass supreme"),
        new Car(15.5, 8, 318.0, 145.0, 4140.0, 13.7, 1977, "dodge monaco brougham"),
        new Car(15.0, 8, 302.0, 130.0, 4295.0, 14.9, 1977, "mercury cougar brougham"),
        new Car(17.5, 6, 250.0, 110.0, 3520.0, 16.4, 1977, "chevrolet concours"),
        new Car(20.5, 6, 231.0, 105.0, 3425.0, 16.9, 1977, "buick skylark"),
        new Car(19.0, 6, 225.0, 100.0, 3630.0, 17.7, 1977, "plymouth volare custom"),
        new Car(18.5, 6, 250.0, 98.00, 3525.0, 19.0, 1977, "ford granada"),
        new Car(16.0, 8, 400.0, 180.0, 4220.0, 11.1, 1977, "pontiac grand prix lj"),
        new Car(15.5, 8, 350.0, 170.0, 4165.0, 11.4, 1977, "chevrolet monte carlo landau"),
        new Car(15.5, 8, 400.0, 190.0, 4325.0, 12.2, 1977, "chrysler cordoba"),
        new Car(16.0, 8, 351.0, 149.0, 4335.0, 14.5, 1977, "ford thunderbird"),
        new Car(29.0, 4, 97.00, 78.00, 1940.0, 14.5, 1977, "volkswagen rabbit custom"),
        new Car(24.5, 4, 151.0, 88.00, 2740.0, 16.0, 1977, "pontiac sunbird coupe"),
        new Car(26.0, 4, 97.00, 75.00, 2265.0, 18.2, 1977, "toyota corolla liftback"),
        new Car(25.5, 4, 140.0, 89.00, 2755.0, 15.8, 1977, "ford mustang ii 2+2"),
        new Car(30.5, 4, 98.00, 63.00, 2051.0, 17.0, 1977, "chevrolet chevette"),
        new Car(33.5, 4, 98.00, 83.00, 2075.0, 15.9, 1977, "dodge colt m/m"),
        new Car(30.0, 4, 97.00, 67.00, 1985.0, 16.4, 1977, "subaru dl"),
        new Car(30.5, 4, 97.00, 78.00, 2190.0, 14.1, 1977, "volkswagen dasher"),
        new Car(22.0, 6, 146.0, 97.00, 2815.0, 14.5, 1977, "datsun 810"),
        new Car(21.5, 4, 121.0, 110.0, 2600.0, 12.8, 1977, "bmw 320i"),
        new Car(21.5, 8,  0.00, 110.0, 2720.0, 13.5, 1977, "mazda rx-4"),
        new Car(43.1, 4, 90.00, 48.00, 1985.0, 21.5, 1978, "volkswagen rabbit custom diesel"),
        new Car(36.1, 4, 98.00, 66.00, 1800.0, 14.4, 1978, "ford fiesta"),
        new Car(32.8, 4, 78.00, 52.00, 1985.0, 19.4, 1978, "mazdaglc deluxe"),
        new Car(39.4, 4, 85.00, 70.00, 2070.0, 18.6, 1978, "datsun b210 gx"),
        new Car(36.1, 4, 91.00, 60.00, 1800.0, 16.4, 1978, "honda civic cvcc"),
        new Car(19.9, 8, 260.0, 110.0, 3365.0, 15.5, 1978, "oldsmobile cutlass salon brougham"),
        new Car(19.4, 8, 318.0, 140.0, 3735.0, 13.2, 1978, "dodge diplomat"),
        new Car(20.2, 8, 302.0, 139.0, 3570.0, 12.8, 1978, "mercury monarch ghia"),
        new Car(19.2, 6, 231.0, 105.0, 3535.0, 19.2, 1978, "pontiac phoenix lj"),
        new Car(20.5, 6, 200.0, 95.00, 3155.0, 18.2, 1978, "chevrolet malibu"),
        new Car(20.2, 6, 200.0, 85.00, 2965.0, 15.8, 1978, "ford fairmont (auto)"),
        new Car(25.1, 4, 140.0, 88.00, 2720.0, 15.4, 1978, "ford fairmont (man)"),
        new Car(20.5, 6, 225.0, 100.0, 3430.0, 17.2, 1978, "plymouth volare"),
        new Car(19.4, 6, 232.0, 90.00, 3210.0, 17.2, 1978, "amc concord"),
        new Car(20.6, 6, 231.0, 105.0, 3380.0, 15.8, 1978, "buick century special"),
        new Car(20.8, 6, 200.0, 85.00, 3070.0, 16.7, 1978, "mercury zephyr"),
        new Car(18.6, 6, 225.0, 110.0, 3620.0, 18.7, 1978, "dodge aspen"),
        new Car(18.1, 6, 258.0, 120.0, 3410.0, 15.1, 1978, "amc concord d/l"),
        new Car(19.2, 8, 305.0, 145.0, 3425.0, 13.2, 1978, "chevrolet monte carlo landau"),
        new Car(17.7, 6, 231.0, 165.0, 3445.0, 13.4, 1978, "buick regal sport coupe (turbo)"),
        new Car(18.1, 8, 302.0, 139.0, 3205.0, 11.2, 1978, "ford futura"),
        new Car(17.5, 8, 318.0, 140.0, 4080.0, 13.7, 1978, "dodge magnum xe"),
        new Car(30.0, 4, 98.00, 68.00, 2155.0, 16.5, 1978, "chevrolet chevette"),
        new Car(27.5, 4, 134.0, 95.00, 2560.0, 14.2, 1978, "toyota corona"),
        new Car(27.2, 4, 119.0, 97.00, 2300.0, 14.7, 1978, "datsun 510"),
        new Car(30.9, 4, 105.0, 75.00, 2230.0, 14.5, 1978, "dodge omni"),
        new Car(21.1, 4, 134.0, 95.00, 2515.0, 14.8, 1978, "toyota celica gt liftback"),
        new Car(23.2, 4, 156.0, 105.0, 2745.0, 16.7, 1978, "plymouth sapporo"),
        new Car(23.8, 4, 151.0, 85.00, 2855.0, 17.6, 1978, "oldsmobile starfire sx"),
        new Car(23.9, 4, 119.0, 97.00, 2405.0, 14.9, 1978, "datsun 200-sx"),
        new Car(20.3, 5, 131.0, 103.0, 2830.0, 15.9, 1978, "audi 5000"),
        new Car(17.0, 6, 163.0, 125.0, 3140.0, 13.6, 1978, "volvo 264gl"),
        new Car(21.6, 4, 121.0, 115.0, 2795.0, 15.7, 1978, "saab 99gle"),
        new Car(16.2, 6, 163.0, 133.0, 3410.0, 15.8, 1978, "peugeot 604sl"),
        new Car(31.5, 4, 89.00, 71.00, 1990.0, 14.9, 1978, "volkswagen scirocco"),
        new Car(29.5, 4, 98.00, 68.00, 2135.0, 16.6, 1978, "honda accord lx"),
        new Car(21.5, 6, 231.0, 115.0, 3245.0, 15.4, 1979, "pontiac lemans v6"),
        new Car(19.8, 6, 200.0, 85.00, 2990.0, 18.2, 1979, "mercury zephyr 6"),
        new Car(22.3, 4, 140.0, 88.00, 2890.0, 17.3, 1979, "ford fairmont 4"),
        new Car(20.2, 6, 232.0, 90.00, 3265.0, 18.2, 1979, "amc concord dl 6"),
        new Car(20.6, 6, 225.0, 110.0, 3360.0, 16.6, 1979, "dodge aspen 6"),
        new Car(17.0, 8, 305.0, 130.0, 3840.0, 15.4, 1979, "chevrolet caprice classic"),
        new Car(17.6, 8, 302.0, 129.0, 3725.0, 13.4, 1979, "ford ltd landau"),
        new Car(16.5, 8, 351.0, 138.0, 3955.0, 13.2, 1979, "mercury grand marquis"),
        new Car(18.2, 8, 318.0, 135.0, 3830.0, 15.2, 1979, "dodge st.0 regis"),
        new Car(16.9, 8, 350.0, 155.0, 4360.0, 14.9, 1979, "buick estate wagon (sw)"),
        new Car(15.5, 8, 351.0, 142.0, 4054.0, 14.3, 1979, "ford country squire (sw)"),
        new Car(19.2, 8, 267.0, 125.0, 3605.0, 15.0, 1979, "chevrolet malibu classic (sw)"),
        new Car(18.5, 8, 360.0, 150.0, 3940.0, 13.0, 1979, "chrysler lebaron town & country (sw)"),
        new Car(31.9, 4, 89.00, 71.00, 1925.0, 14.0, 1979, "vw rabbit custom"),
        new Car(34.1, 4, 86.00, 65.00, 1975.0, 15.2, 1979, "maxdaglc deluxe"),
        new Car(35.7, 4, 98.00, 80.00, 1915.0, 14.4, 1979, "dodge colt hatchback custom"),
        new Car(27.4, 4, 121.0, 80.00, 2670.0, 15.0, 1979, "amc spirit dl"),
        new Car(25.4, 5, 183.0, 77.00, 3530.0, 20.1, 1979, "mercedes benz 300d"),
        new Car(23.0, 8, 350.0, 125.0, 3900.0, 17.4, 1979, "cadillac eldorado"),
        new Car(27.2, 4, 141.0, 71.00, 3190.0, 24.8, 1979, "peugeot 504"),
        new Car(23.9, 8, 260.0, 90.00, 3420.0, 22.2, 1979, "oldsmobile cutlass salon brougham"),
        new Car(34.2, 4, 105.0, 70.00, 2200.0, 13.2, 1979, "plymouth horizon"),
        new Car(34.5, 4, 105.0, 70.00, 2150.0, 14.9, 1979, "plymouth horizon tc3"),
        new Car(31.8, 4, 85.00, 65.00, 2020.0, 19.2, 1979, "datsun 210"),
        new Car(37.3, 4, 91.00, 69.00, 2130.0, 14.7, 1979, "fiat strada custom"),
        new Car(28.4, 4, 151.0, 90.00, 2670.0, 16.0, 1979, "buick skylark limited"),
        new Car(28.8, 6, 173.0, 115.0, 2595.0, 11.3, 1979, "chevrolet citation"),
        new Car(26.8, 6, 173.0, 115.0, 2700.0, 12.9, 1979, "oldsmobile omega brougham"),
        new Car(33.5, 4, 151.0, 90.00, 2556.0, 13.2, 1979, "pontiac phoenix"),
        new Car(41.5, 4, 98.00, 76.00, 2144.0, 14.7, 1980, "vw rabbit"),
        new Car(38.1, 4, 89.00, 60.00, 1968.0, 18.8, 1980, "toyota corolla tercel"),
        new Car(32.1, 4, 98.00, 70.00, 2120.0, 15.5, 1980, "chevrolet chevette"),
        new Car(37.2, 4, 86.00, 65.00, 2019.0, 16.4, 1980, "datsun 310"),
        new Car(28.0, 4, 151.0, 90.00, 2678.0, 16.5, 1980, "chevrolet citation"),
        new Car(26.4, 4, 140.0, 88.00, 2870.0, 18.1, 1980, "ford fairmont"),
        new Car(24.3, 4, 151.0, 90.00, 3003.0, 20.1, 1980, "amc concord"),
        new Car(19.1, 6, 225.0, 90.00, 3381.0, 18.7, 1980, "dodge aspen"),
        new Car(34.3, 4, 97.00, 78.00, 2188.0, 15.8, 1980, "audi 4000"),
        new Car(29.8, 4, 134.0, 90.00, 2711.0, 15.5, 1980, "toyota corona liftback"),
        new Car(31.3, 4, 120.0, 75.00, 2542.0, 17.5, 1980, "mazda 626"),
        new Car(37.0, 4, 119.0, 92.00, 2434.0, 15.0, 1980, "datsun 510 hatchback"),
        new Car(32.2, 4, 108.0, 75.00, 2265.0, 15.2, 1980, "toyota corolla"),
        new Car(46.6, 4, 86.00, 65.00, 2110.0, 17.9, 1980, "mazda glc"),
        new Car(27.9, 4, 156.0, 105.0, 2800.0, 14.4, 1980, "dodge colt"),
        new Car(40.8, 4, 85.00, 65.00, 2110.0, 19.2, 1980, "datsun 210"),
        new Car(44.3, 4, 90.00, 48.00, 2085.0, 21.7, 1980, "vw rabbit c (diesel)"),
        new Car(43.4, 4, 90.00, 48.00, 2335.0, 23.7, 1980, "vw dasher (diesel)"),
        new Car(36.4, 5, 121.0, 67.00, 2950.0, 19.9, 1980, "audi 5000s (diesel)"),
        new Car(30.0, 4, 146.0, 67.00, 3250.0, 21.8, 1980, "mercedes-benz 240d"),
        new Car(44.6, 4, 91.00, 67.00, 1850.0, 13.8, 1980, "honda civic 1500 gl"),
        new Car(40.9, 4, 85.00,    NA, 1835.0, 17.3, 1980, "renault lecar deluxe"),
        new Car(33.8, 4, 97.00, 67.00, 2145.0, 18.0, 1980, "subaru dl"),
        new Car(29.8, 4, 89.00, 62.00, 1845.0, 15.3, 1980, "vokswagen rabbit"),
        new Car(32.7, 6, 168.0, 132.0, 2910.0, 11.4, 1980, "datsun 280-zx"),
        new Car(23.7, 7,  0.00, 100.0, 2420.0, 12.5, 1980, "mazda rx-7 gs"),
        new Car(35.0, 4, 122.0, 88.00, 2500.0, 15.1, 1980, "triumph tr7 coupe"),
        new Car(23.6, 4, 140.0,    NA, 2905.0, 14.3, 1980, "ford mustang cobra"),
        new Car(32.4, 4, 107.0, 72.00, 2290.0, 17.0, 1980, "honda accord"),
        new Car(27.2, 4, 135.0, 84.00, 2490.0, 15.7, 1981, "plymouth reliant"),
        new Car(26.6, 4, 151.0, 84.00, 2635.0, 16.4, 1981, "buick skylark"),
        new Car(25.8, 4, 156.0, 92.00, 2620.0, 14.4, 1981, "dodge aries wagon (sw)"),
        new Car(23.5, 6, 173.0, 110.0, 2725.0, 12.6, 1981, "chevrolet citation"),
        new Car(30.0, 4, 135.0, 84.00, 2385.0, 12.9, 1981, "plymouth reliant"),
        new Car(39.1, 4, 79.00, 58.00, 1755.0, 16.9, 1981, "toyota starlet"),
        new Car(39.0, 4, 86.00, 64.00, 1875.0, 16.4, 1981, "plymouth champ"),
        new Car(35.1, 4, 81.00, 60.00, 1760.0, 16.1, 1981, "honda civic 1300"),
        new Car(32.3, 4, 97.00, 67.00, 2065.0, 17.8, 1981, "subaru"),
        new Car(37.0, 4, 85.00, 65.00, 1975.0, 19.4, 1981, "datsun 210 mpg"),
        new Car(37.7, 4, 89.00, 62.00, 2050.0, 17.3, 1981, "toyota tercel"),
        new Car(34.1, 4, 91.00, 68.00, 1985.0, 16.0, 1981, "mazdaglc 4"),
        new Car(34.7, 4, 105.0, 63.00, 2215.0, 14.9, 1981, "plymouth horizon 4"),
        new Car(34.4, 4, 98.00, 65.00, 2045.0, 16.2, 1981, "ford escort 4w"),
        new Car(29.9, 4, 98.00, 65.00, 2380.0, 20.7, 1981, "ford escort 2h"),
        new Car(33.0, 4, 105.0, 74.00, 2190.0, 14.2, 1981, "volkswagen jetta"),
        new Car(34.5, 4, 100.0,    NA, 2320.0, 15.8, 1981, "renault 18i"),
        new Car(33.7, 4, 107.0, 75.00, 2210.0, 14.4, 1981, "honda prelude"),
        new Car(32.4, 4, 108.0, 75.00, 2350.0, 16.8, 1981, "toyota corolla"),
        new Car(32.9, 4, 119.0, 100.0, 2615.0, 14.8, 1981, "datsun 200sx"),
        new Car(31.6, 4, 120.0, 74.00, 2635.0, 18.3, 1981, "mazda 626"),
        new Car(28.1, 4, 141.0, 80.00, 3230.0, 20.4, 1981, "peugeot 505s turbo diesel"),
        new Car(30.7, 6, 145.0, 76.00, 3160.0, 19.6, 1981, "volvo diesel"),
        new Car(25.4, 6, 168.0, 116.0, 2900.0, 12.6, 1981, "toyota cressida"),
        new Car(24.2, 6, 146.0, 120.0, 2930.0, 13.8, 1981, "datsun 810 maxima"),
        new Car(22.4, 6, 231.0, 110.0, 3415.0, 15.8, 1981, "buick century"),
        new Car(26.6, 8, 350.0, 105.0, 3725.0, 19.0, 1981, "oldsmobile cutlass ls"),
        new Car(20.2, 6, 200.0, 88.00, 3060.0, 17.1, 1981, "ford granada gl"),
        new Car(17.6, 6, 225.0, 85.00, 3465.0, 16.6, 1981, "chrysler lebaron salon"),
        new Car(28.0, 4, 112.0, 88.00, 2605.0, 19.6, 1982, "chevrolet cavalier"),
        new Car(27.0, 4, 112.0, 88.00, 2640.0, 18.6, 1982, "chevrolet cavalier wagon"),
        new Car(34.0, 4, 112.0, 88.00, 2395.0, 18.0, 1982, "chevrolet cavalier 2-door"),
        new Car(31.0, 4, 112.0, 85.00, 2575.0, 16.2, 1982, "pontiac j2000 se hatchback"),
        new Car(29.0, 4, 135.0, 84.00, 2525.0, 16.0, 1982, "dodge aries se"),
        new Car(27.0, 4, 151.0, 90.00, 2735.0, 18.0, 1982, "pontiac phoenix"),
        new Car(24.0, 4, 140.0, 92.00, 2865.0, 16.4, 1982, "ford fairmont futura"),
        new Car(23.0, 4, 151.0,    NA, 3035.0, 20.5, 1982, "amc concord dl"),
        new Car(36.0, 4, 105.0, 74.00, 1980.0, 15.3, 1982, "volkswagen rabbit l"),
        new Car(37.0, 4, 91.00, 68.00, 2025.0, 18.2, 1982, "mazdaglc custom l"),
        new Car(31.0, 4, 91.00, 68.00, 1970.0, 17.6, 1982, "mazdaglc custom"),
        new Car(38.0, 4, 105.0, 63.00, 2125.0, 14.7, 1982, "plymouth horizon miser"),
        new Car(36.0, 4, 98.00, 70.00, 2125.0, 17.3, 1982, "mercury lynx l"),
        new Car(36.0, 4, 120.0, 88.00, 2160.0, 14.5, 1982, "nissan stanza xe"),
        new Car(36.0, 4, 107.0, 75.00, 2205.0, 14.5, 1982, "honda accord"),
        new Car(34.0, 4, 108.0, 70.00, 2245.0, 16.9, 1982, "toyota corolla"),
        new Car(38.0, 4, 91.00, 67.00, 1965.0, 15.0, 1982, "honda civic"),
        new Car(32.0, 4, 91.00, 67.00, 1965.0, 15.7, 1982, "honda civic (auto)"),
        new Car(38.0, 4, 91.00, 67.00, 1995.0, 16.2, 1982, "datsun 310 gx"),
        new Car(25.0, 6, 181.0, 110.0, 2945.0, 16.4, 1982, "buick century limited"),
        new Car(38.0, 6, 262.0, 85.00, 3015.0, 17.0, 1982, "oldsmobile cutlass ciera (diesel)"),
        new Car(26.0, 4, 156.0, 92.00, 2585.0, 14.5, 1982, "chrysler lebaron medallion"),
        new Car(22.0, 6, 232.0, 112.0, 2835.0, 14.7, 1982, "ford granada l"),
        new Car(32.0, 4, 144.0, 96.00, 2665.0, 13.9, 1982, "toyota celica gt"),
        new Car(36.0, 4, 135.0, 84.00, 2370.0, 13.0, 1982, "dodge charger 2.2"),
        new Car(27.0, 4, 151.0, 90.00, 2950.0, 17.3, 1982, "chevrolet camaro"),
        new Car(27.0, 4, 140.0, 86.00, 2790.0, 15.6, 1982, "ford mustang gl"),
        new Car(44.0, 4, 97.00, 52.00, 2130.0, 24.6, 1982, "vw pickup"),
        new Car(32.0, 4, 135.0, 84.00, 2295.0, 11.6, 1982, "dodge rampage"),
        new Car(28.0, 4, 120.0, 79.00, 2625.0, 18.6, 1982, "ford ranger"),
        new Car(31.0, 4, 119.0, 82.00, 2720.0, 19.4, 1982, "chevy s-10")
    };

	private static final int dataSetSize = Car.data.length;
    private static int testingDataSetSize = (dataSetSize + 3)/ 4;
	private static int trainingDataSetSize = dataSetSize - testingDataSetSize;

	public static Car[] trainingData = new Car[trainingDataSetSize];
    public static Car[] testingData = new Car[testingDataSetSize];

	static {
		int j = 0, k = 0;
		for (int i = 0; i < Car.dataSetSize; i++) {
			if (i % 4 == 0) {
				testingData[k++] = data[i];
			} else {
				trainingData[j++] = data[i];
			}
		}
	}

}
