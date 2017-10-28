/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.lol.controller;

import br.ufpr.tads.dac.lol.facede.CrudFacede;
import br.ufpr.tads.dac.lol.facede.FuncionarioFacede;
import br.ufpr.tads.dac.lol.model.Funcionario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Lucas
 */
@WebServlet(name = "FuncionarioController", urlPatterns = {"/funcionario/*"})
public class FuncionarioController extends CrudController<Funcionario> {

    private static final Logger logger = LoggerFactory.getLogger(FuncionarioController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }

    @Override
    protected Funcionario getModel() {
        return new Funcionario();
    }

    @Override
    protected CrudFacede<Funcionario> getFacede() {
        return new FuncionarioFacede();
    }

    @Override
    protected String getBasePath() {
        return "funcionario";
    }
}
