package mvc.controls;


import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Ivan Sliusar on 22.01.2018.
 * Red Line Soft corp.
 */
public class SigninConrollerTest {
    /** Servlet under test. */
    private SigninConroller servlet;

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
        parameters = new HashMap();
        servlet = new SigninConroller();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        dispatcher = mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
        when(request.getParameterMap()).thenReturn(parameters);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(request.getParameter(anyString())).thenAnswer(new Answer() {
            /**
             * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.InvocationOnMock)
             */
            @Override
            public Object answer(InvocationOnMock aInvocation) throws Throwable {
                String key = (String) aInvocation.getArguments()[0];
                return parameters.get(key);
            }
        });

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
    public void whenLoginInBaseThenTrue() throws Exception {
        setUp();

        parameters.put("login","simvip");
        servlet.doPost(request,response);

        assertThat((String) attributes.get("login"),is("simvip"));

        parameters.put("login","noname");
        servlet.doPost(request,response);

        assertTrue((Boolean) attributes.get("error"));

    }

}