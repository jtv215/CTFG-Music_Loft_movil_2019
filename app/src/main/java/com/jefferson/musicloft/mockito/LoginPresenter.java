package com.jefferson.musicloft.mockito;

public class LoginPresenter implements  Login.Presenter{

    private Login.View view;
    private Login.Model model;

    public LoginPresenter(Login.View view) {
        this.view = view;
        model = new LoginModel(this);

    }
    @Override
    public void validaUser(String user, String password) {

    }



    @Override
    public void usuarioValido() {
        if(view != null){
            view.usuariovalido();
        }
    }

    @Override
    public void error() {
        if(view != null){
           view.error();
        }
    }
}
