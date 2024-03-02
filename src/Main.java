//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        ExchangeCoventer converter = new exchangeConverterImp(2.0);
        Exchange exchangeRate = new Exchange(converter);
        double amount = 2.0;
        double result = exchangeRate.convertCurrency(amount);
        System.out.println(amount + " USD is equal to " + result + " target currency");
    }

    }
