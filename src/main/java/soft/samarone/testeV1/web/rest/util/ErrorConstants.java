package soft.samarone.testeV1.web.rest.util;

import java.net.URI;

public final class ErrorConstants {

    public static final String ERR_VALIDATION = "error.validation";
    public static final String BASE_URL = "http://soft.samarone.testeV1/problem";
    public static final String ERR_BUSINESS = "error.business";
    public static final URI DEFAULT_TYPE = URI.create(BASE_URL + "/problem-with-message");
    public static final URI CONSTRAINT_VIOLATION_TYPE = URI.create(BASE_URL + "/contraint-violation");
    public static final URI BUSINESS_VALIDATION_TYPE = URI.create(BASE_URL + "/business-validation");
    public static final URI PARAMETERIZED_TYPE = URI.create(BASE_URL + "/parameterized");

    public static final String ERROR_400 = "Erro (4xx) ao tentar executar a API %s.";
    public static final String ERROR_500 = "Erro (5xx) interno ao tentar executar a API %s.";
    public static final String GENERIC_ERROR = "Erro inesperado ao tentar executar a API %s.";

    private ErrorConstants() {}

}
