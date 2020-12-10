package com.jefferson.musicloft.mockito;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {


    @Mock
    private Login.View view;

    @Mock
    private Login.Model model;
    private LoginPresenter presenter;

    @Before void setUp() throws Exception{
        presenter = new LoginPresenter(view);

    }

    @Test
    public void shouldShowErrorMessageWhenUserOPassswordIsWrongTest() throws Exception{
        //Nos permite programar un comportamiento
        when(view.getUserName()).thenReturn("maria");
        when(view.getPassword()).thenReturn("asdf");
        //Ejecutamos un metodo
        presenter.validaUser(view.getUserName(),view.getPassword());
        //verifica que la vista ejecuta el metodo error.
        verify(view).error();
    }

}