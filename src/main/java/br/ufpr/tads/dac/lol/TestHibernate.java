package br.ufpr.tads.dac.lol;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.ufpr.tads.dac.lol.data.HibernateUtil;
import br.ufpr.tads.dac.lol.model.Cliente;

public class TestHibernate {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        System.out.println("Hibernate many to many - join table + extra column (Annotation)");
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Query query = session.createQuery("delete Cliente");
        query.executeUpdate();
        
        Cliente cliente = new Cliente();
        cliente.setNome("Lucas");
        cliente.setEmail("marques@gmail");
        cliente.setCpf("123456");
        cliente.setAtivo((byte) 0x1);
        session.save(cliente);

        cliente = new Cliente();
        cliente.setNome("Flávio");
        cliente.setEmail("dallegrave@gmail");
        cliente.setCpf("3333333");
        cliente.setAtivo((byte) 0x0);
        session.save(cliente);

        session.getTransaction().commit();

        System.out.println("Ativos");
        query = session.getNamedQuery("Cliente.findByAtivo2")
                .setByte("ativo", (byte) 0x1);
        List<Cliente> list = query.list();
        for (Cliente c : list) {
            System.out.println(c.getNome());
        }

        System.out.println("Inativos");
        query = session.getNamedQuery("Cliente.findByAtivo2")
                .setByte("ativo", (byte) 0x0);
        list = query.list();
        for (Cliente c : list) {
            System.out.println(c.getNome());
        }

        System.out.println("Done");
        // Prep work
        // SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        // Session session = sessionFactory.getCurrentSession();
        //
        // //HQL Named Query Example
        // Query query = session.getNamedQuery("HQL_GET_ALL_EMPLOYEE");
        // List<Employee> empList = query.list();
        // for (Employee emp : empList) {
        // System.out.println("List of Employees::" + emp.getId() + ","
        // + emp.getAddress().getCity());
        // }
        //
        // query = session.getNamedQuery("HQL_GET_EMPLOYEE_BY_ID");
        // query.setInteger("id", 2);
        // Employee emp = (Employee) query.uniqueResult();
        // System.out.println("Employee Name=" + emp.getName() + ", City="
        // + emp.getAddress().getCity());
        //
        // query = session.getNamedQuery("HQL_GET_EMPLOYEE_BY_SALARY");
        // query.setInteger("salary", 200);
        // empList = query.list();
        // for (Employee emp1 : empList) {
        // System.out.println("List of Employees::" + emp1.getId() + ","
        // + emp1.getSalary());
        // }
        //
        // query = session.getNamedQuery("@HQL_GET_ALL_ADDRESS");
        // List<Address> addressList = query.list();
        // for (Address addr : addressList) {
        // System.out.println("List of Address::" + addr.getId() + "::"
        // + addr.getZipcode() + "::" + addr.getEmployee().getName());
        // }
        //
        // //Native SQL Named Query Example
        // query = session.getNamedQuery("@SQL_GET_ALL_ADDRESS");
        // List<Object[]> addressObjArray = query.list();
        // for(Object[] row : addressObjArray){
        // for(Object obj : row){
        // System.out.print(obj + "::");
        // }
        // System.out.println("\n");
        // }
        //
        // query = session.getNamedQuery("SQL_GET_ALL_EMP_ADDRESS");
        // addressObjArray = query.list();
        // for(Object[] row : addressObjArray){
        // Employee e = (Employee) row[0];
        // System.out.println("Employee Info::"+e);
        // Address a = (Address) row[1];
        // System.out.println("Address Info::"+a);
        // }
        // // rolling back to save the test data
        // tx.commit();
        //
        // // closing hibernate resources
        // sessionFactory.close();
    }

}
