module ba.reservation.airlinemanagement {
    requires javafx.controls;
    requires java.naming;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires java.persistence;
    requires java.xml.bind;
    requires java.sql;
    requires java.sql.rowset;
    requires org.hibernate.orm.core;
    requires org.hibernate.commons.annotations;

    opens ba.reservation.airlinemanagement to javafx.xml;
    opens ba.reservation.airlinemanagement.bussines.model to org.hibernate.orm.core;

    exports ba.reservation.airlinemanagement;
    exports ba.reservation.airlinemanagement.bussines.model;
   // exports ba.reservation.airlinemanagement.gui;
  //  opens ba.reservation.airlinemanagement.gui to javafx.xml;

}