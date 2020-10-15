import java.text.DecimalFormat;

public class Vector {

	private double[] vector;

	public Vector(int length) {
		this.vector = new double[length];
	}

	public Vector(Vector v) {
		this(v.length());
		for (int i = 0; i < v.length(); i++) {
			this.vector[i] = v.vector[i];
		}
	}

	public Vector(int length, double value) {
		this(length);
		for (int i = 0; i < length; i++) {
			this.vector[i] = value;
		}
	}

	public Vector(double[] values) {
		this.vector = new double[values.length];
		for (int i = 0; i < values.length; i++) {
			vector[i] = values[i];
		}
	}

	public int length() {
		return this.vector.length;
	}

	public double get(int index) {
		return this.vector[index];
	}

	public void set(int index, double value) {
		this.vector[index] = value;
	}

	public void add(int index, double value) {
		this.vector[index] += value;
	}

	public double norm() { // Square of the L2 norm
		double result = 0.0;
		for (int i = 0; i < this.length(); i++) {
			result += this.vector[i] * this.vector[i];
		}
		return result;
	}

	public Vector add(Vector other) {
		assert other.length() == this.length();
		Vector result = new Vector(this.length());
		for (int i = 0; i < this.length(); i++) {
			result.vector[i] = this.vector[i] + other.vector[i];
		}
		return result;
	}

	public Vector subtract(Vector other) {
		assert other.length() == this.length();
		Vector result = new Vector(this.length());
		for (int i = 0; i < this.length(); i++) {
			result.vector[i] = this.vector[i] - other.vector[i];
		}
		return result;
	}

	public Vector scale(double factor) {
		Vector result = new Vector(this.length());
		for (int i = 0; i < this.length(); i++) {
			result.vector[i] = factor * this.vector[i];
		}
		return result;
	}

	public double dot(Vector other) { // Dot product
		double result = 0.0;
		for (int i = 1; i < this.length(); i++) {
			result += this.vector[i] * other.vector[i];
		}
		return result;
	}



	public boolean equals(Vector other) {
		if (this.length() != other.length()) return false;
		for (int i = 0; i < this.length(); i++) {
			if (this.vector[i] != other.vector[i]) return false;
		}
		return true;
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof Vector && equals((Vector) other);
	}

	@Override
	public String toString() {
		DecimalFormat format = new DecimalFormat("#.##E0");
		String result = "(";
		String separator = "";
		for (int i = 0; i < this.length(); i++) {
			result += separator;
			result += format.format(this.vector[i]);
			separator = ", ";
		}
		result += ")";
		return result;
	}
}
