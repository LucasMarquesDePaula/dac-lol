package br.ufpr.tads.dac.lol.facede;

/**
 *
 * @author Lucas
 */
public class ValidationUtil {

    /**
     *
     * @param text
     * @return true if text is not empty
     */
    public static boolean checkText(String text) {
        return text != null && !text.trim().isEmpty();
    }

    /**
     *
     * @param cpf
     * @return true if cpf is valid
     */
    public static boolean checkCpf(String cpf) {
        int sm, peso, num, r;
        char dig10, dig11;

        // Valida a mascara
        if (!cpf.replaceAll("[0-9]", "").equals("..-")) {
            return false;
        }

        // Remove a mascara
        cpf = cpf.replaceAll("[^0-9]", "");

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (int i = 0; i < 9; i++) {
                num = Integer.parseInt(cpf.substring(i, i + 1));
                sm = sm + num * peso;
                peso = peso - 1;
            }

            r = 11 - sm % 11;
            if (r == 10 || r == 11) {
                dig10 = '0';
            } else {
                dig10 = Integer.toString(r).charAt(0);
            }

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (int i = 0; i < 10; i++) {
                num = Integer.parseInt(cpf.substring(i, i + 1));
                sm = sm + num * peso;
                peso = peso - 1;
            }

            r = 11 - sm % 11;
            if (r == 10 || r == 11) {
                dig11 = '0';
            } else {
                dig11 = Integer.toString(r).charAt(0);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            return cpf.charAt(9) == dig10 && cpf.charAt(10) == dig11;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }

    /**
     *
     * @param email
     * @return true if email is valid
     */
    public static boolean checkEmail(String email) {
        // TODO
        return checkText(email);
    }
}
