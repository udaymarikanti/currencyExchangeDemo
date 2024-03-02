public class exchangeConverterImp implements ExchangeCoventer {
        private double exchangeRate;

        public exchangeConverterImp(double exchangeRate) {
            this.exchangeRate = exchangeRate;
        }

    @Override
    public double convertCurrency(double exchangeRate, double amount) {
        return amount * exchangeRate;
    }
}

