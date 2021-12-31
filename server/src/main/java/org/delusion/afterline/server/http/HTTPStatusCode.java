package org.delusion.afterline.server.http;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum HTTPStatusCode {
    Continue(100),
    SwitchingProtocols(101),
    EarlyHints(103),
    Processing(102),
    Ok(200),
    Created(201),
    Accepted(202),
    NonAuthoritativeInformation(203),
    NoContent(204),
    ResetContent(205),
    PartialContent(206),
    MultiStatus(207),
    AlreadyReported(208),
    IMUsed(226),
    MultipleChoice(300),
    MovedPermanently(301),
    Found(302),
    SeeOther(303),
    NotModified(304),
    TemporaryRedirect(307),
    PermanentRedirect(308),
    BadRequest(400),
    Unauthorized(401),
    Forbidden(403),
    NotFound(404),
    MethodNotAllowed(405),
    NotAcceptable(406),
    ProxyAuthenticationRequired(407),
    RequestTimeout(408),
    Conflict(409),
    Gone(410),
    LengthRequired(411),
    PreconditionFailed(412),
    PayloadToLarge(413),
    URITooLong(414),
    UnsupportedMediaType(415),
    RangeNotSatisfiable(416),
    ExpectationFailed(417),
    ImATeapot(418),
    MisdirectedRequest(421),
    UnprocessableEntity(422),
    Locked(423),
    FailedDependency(424),
    TooEarly(425),
    UpgradeRequired(426),
    PreconditionRequired(428),
    TooManyRequests(429),
    RequestHeaderFieldsTooLarge(431),
    UnavailableForLegalReasons(451),
    InternalServerError(500),
    NotImplemented(501),
    BadGateway(502),
    ServiceUnavailable(503),
    GatewayTimeout(504),
    HTTPVersionNotSupported(505),
    VariantAlsoNegotiates(506),
    InsufficientStorage(507),
    LoopDetected(508),
    NotExtended(510),
    NetworkAuthenticationRequired(511);

    public final int code;

    private static Map<Integer, HTTPStatusCode> statusCodesByNumber;

    HTTPStatusCode(int i) {
        code = i;
    }

    public static HTTPStatusCode getCodeFromID(int id) {
        if (statusCodesByNumber == null) {
            // initialize map
            statusCodesByNumber = new HashMap<>();
            Arrays.stream(values()).forEach(sc -> statusCodesByNumber.put(sc.code, sc));
        }

        return statusCodesByNumber.getOrDefault(id, BadRequest);
    }



}
