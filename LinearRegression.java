public class LinearRegression {

    public Vector weights;
    public Vector stepSizes;
    public Vector standardDevs;
    public int dataPoints;
    public int attributes;
    public boolean done;

    public void regress(Car[] trainingdata, int attributes, int dataPoints, double[] standardDevs) {
        this.standardDevs = new Vector(standardDevs);
        this.attributes = attributes;
        this.dataPoints = dataPoints;
        double[] weights = new double[attributes];

        for (int i = 0; i < weights.length; i++) {
            weights[i] = 0;
        }
        this.weights = new Vector(weights);

        double[] stepSizes = new double[attributes];
        for (int i = 0; i < stepSizes.length; i++) {
            stepSizes[i] = 1 / 10_000.0;
        }
        this.stepSizes = new Vector(stepSizes);


        double sum = 0;
        while (!done) {
            for (int j = 0; j < this.dataPoints; j++) {
                for (int i = 0; i < this.attributes; i++) { //looping over all weights
                    sum = 0;
                    for (int k = 0; k < j; k++) { //looping over all X values
                        Car c = trainingdata[j];
                        Vector v = c.getNormalizedAttributes();
                        sum += (c.normalizedMPG() - this.weights.dot(v)) * v.get(i);
                        //System.out.println("mpg " + c.normalizedMPG() + ", " + this.weights.dot(v) * v.get(i));
                    }

                    sum /= this.dataPoints;

                    if (sum == 0) {
                        done = true;
                    }

//                    System.out.println("" + i + ", " + (this.stepSizes.get(i) * sum) + ", " + sum);

                    this.weights.set(i, -this.weights.get(i) + this.stepSizes.get(i) * sum);
                }
            }
        }

    }

    public void test() {
        double totalError = 0;
        double error = 0;
        for (Car c : Car.testingData) {
            double d = predict(c);
            error = (int)(100*(c.mpg() - d)/c.mpg());
            System.out.println("prediction: " + d + " actual: " + c.mpg() + "    %error: " + error + "%");
            totalError += Math.abs(error);
        }
        totalError /= Car.testingData.length;
        System.out.println("Total error: " + (int)(totalError) + "%");

    }


    public double predict(Car c) {
        //return weights.dot(c.getAttributes());
        return weights.dot(c.getAttributes()) * (46.6 - 9) + 9;
    }

    public static void main(String[] args) {
        double[] standardDevs = {1.693, 105.362, 38.491, 846.842, 2.758, 3.698};
        LinearRegression lr = new LinearRegression();
        //have to handle with
        lr.regress(Car.trainingData, 6, Car.trainingData.length, standardDevs);
        System.out.println(lr.weights);
        lr.test();


    }


}
