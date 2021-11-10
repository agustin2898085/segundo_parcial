/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controlador;

import com.emergentes.modelo.Seminarios;
import com.emergentes.utiles.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PreparedStatement ps;
        Conexion canal = new Conexion();
        Connection conn = canal.conectar();
        ResultSet rs;
        String op;
        int id;
        ArrayList<Seminarios> lista = new ArrayList<Seminarios>();
        op = (request.getParameter("op") != null) ? request.getParameter("op") : "list";
        if (op.equals("list")) {
            //operacions para listar datos 
            String sql = "select * from seminarios";
            try {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Seminarios lib = new Seminarios();
                    lib.setId(rs.getInt("id"));
                    lib.setTitulo(rs.getString("titulo"));
                    lib.setExpositor(rs.getString("expositor"));
                    lib.setFecha(rs.getString("fecha"));
                    lib.setHora(rs.getString("hora"));
                    lib.setCupo(rs.getInt("cupo"));
                    lista.add(lib);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (op.equals("nuevo")) {
            //operaciones para desplegar formulario
            Seminarios li = new Seminarios();
            request.setAttribute("lib", li);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        if (op.equals("eliminar")) {
            //operaciones para eliminar un registro 
            id = Integer.parseInt(request.getParameter("id"));
            try {
                ps = conn.prepareStatement("delete from seminarios where id=?");
                ps.setInt(1, id);
                ps.executeUpdate();
                response.sendRedirect("MainController");
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(op.equals("editar")){
           id=Integer.parseInt(request.getParameter("id"));
            try {
                Seminarios lib1=new Seminarios();
                ps=conn.prepareStatement("select * from seminarios where id=?");
                ps.setInt(1, id);
                rs=ps.executeQuery();
                if(rs.next()){
                    lib1.setId(rs.getInt("id"));
                    lib1.setTitulo(rs.getString("titulo"));
                    lib1.setExpositor(rs.getString("expositor"));
                    lib1.setFecha(rs.getString("fecha"));
                    lib1.setHora(rs.getString("hora"));
                    lib1.setCupo(rs.getInt("cupo"));
                    
                }
                request.setAttribute("lib", lib1);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         int id = Integer.parseInt(request.getParameter("id"));
        String titulo = request.getParameter("titulo");
        String expositor = request.getParameter("expositor");
        String fecha = request.getParameter("fecha");
        String hora=request.getParameter("hora");
        int cupo = Integer.parseInt(request.getParameter("cupo"));
        Seminarios lib = new Seminarios();
        lib.setId(id);
        lib.setTitulo(titulo);
        lib.setExpositor(expositor);
        lib.setFecha(fecha);
        lib.setHora(hora);
        lib.setCupo(cupo);
        Conexion canal = new Conexion();
        Connection conn = canal.conectar();
        PreparedStatement ps;
        ResultSet rs;
        if (id == 0) {
            //insertar registros
            String sql = "insert into seminarios (titulo,expositor,fecha,hora,cupo) values(?,?,?,?,?)";
            try {
                ps = conn.prepareStatement(sql);
                ps.setString(1, lib.getTitulo());
                ps.setString(2,lib.getExpositor());
                ps.setString(3, lib.getFecha());
                ps.setString(4,lib.getHora());
                ps.setInt(5, lib.getCupo());
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //actualizar registros
            String sql1="update seminarios set titulo=?,expositor=?,fecha=?,hora=?,cupo=? where id=?";
            try {
                ps=conn.prepareStatement(sql1);
                ps.setString(1, lib.getTitulo());
                ps.setString(2,lib.getExpositor());
                ps.setString(3, lib.getFecha());
                ps.setString(4,lib.getHora());
                ps.setInt(5, lib.getCupo());
                ps.setInt(6,lib.getId());
                ps.executeUpdate();             
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        response.sendRedirect("MainController");
    

    }

}
