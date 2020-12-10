package com.jefferson.musicloft.mockito;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTests {

    @Mock
    private Login.View view;

    @Mock
    private Login.Model model;
    private LoginPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new LoginPresenter(view);
    }

    @Test
    public void LoginCorrecto() throws Exception {
        //Nos permite programar un comportamiento
        when(view.getUserName()).thenReturn("jefferson");
        when(view.getPassword()).thenReturn("1234");
        presenter.validaUser(view.getUserName(), view.getPassword());
        //verifica que la vista que ejecuta el método es correcto.
        verify(view).usuariovalido();
    }

    @Test
    public void LoginIncorrecto() throws Exception {
        //Nos permite programar un comportamiento
        when(view.getUserName()).thenReturn("Clasic");
        when(view.getPassword()).thenReturn("1234");
        presenter.validaUser(view.getUserName(), view.getPassword());
        //verifica que la vista que ejecuta el metodo es de error.
        verify(view).error();
    }

}
