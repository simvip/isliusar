package mvc.controls;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Ivan Sliusar on 26.01.2018.
 * Red Line Soft corp.
 */
public class SignOutConrollerTest {
    /** Servlet under test. */
    private SignOutConroller servlet;

    /** Mock dispatcher */
    private RequestDispatcher dispatcher;

    /** Mock request. */
    private HttpServletRequest request;

    /** Mock response. */
    private HttpServletResponse response;

    /** Mock session. */
    private HttpSession session;

    /** Session's attribute map. */
    private Map attributes;

    /** Request's parameter map. */
    private Map parameters;




    public void setUp() {

        attributes = new HashMap();

        servlet = new SignOutConroller();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        dispatcher = mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(request.getParameterMap()).thenReturn(parameters);

        when(session.getAttribute(anyString())).thenAnswer(new Answer() {
            /**
             * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.InvocationOnMock)
             */
            @Override
            public Object answer(InvocationOnMock aInvocation) throws Throwable {
                String key = (String) aInvocation.getArguments()[0];
                return attributes.get(key);
            }
        });
        Mockito.doAnswer(new Answer() {
            /**
             * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.InvocationOnMock)
             */
            @Override
            public Object answer(InvocationOnMock aInvocation) throws Throwable {
                String key = (String) aInvocation.getArguments()[0];
                Object value = aInvocation.getArguments()[1];
                attributes.put(key, value);
                return null;
            }
        }).when(session).setAttribute(anyString(), anyObject());
    }
    @Test
    public void doPost() throws Exception {
        setUp();

        when(request.getRequestDispatcher(anyString())).then(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                attributes.put("LogOutIsDone",true);
                return dispatcher;
            }
        });

        servlet.doPost(request,response);

        assertTrue((Boolean) attributes.get("LogOutIsDone"));
    }

}