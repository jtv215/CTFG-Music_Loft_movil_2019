package com.jefferson.musicloft.mockito;

public class LoginModel implements Login.Model{


    private Login.Presenter presenter;

    public LoginModel(Login.Presenter presenter){
        this.presenter = presenter;

    }

    @Override
    public void validaUser(String user, String password) {
        if(user.equals("jefferson") && password.equals("1234")){
            presenter.usuarioValido();
        }else{
            presenter.error();
        }
    }
}
