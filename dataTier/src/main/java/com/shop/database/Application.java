package com.shop.database;

/**
 * Created by said on 06.05.2017.
 */
public class Application {

    public static void main(String[] args) {

//        ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
//        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
//        JdbcTemplate initializer = new JdbcTemplate(dataSource);

        final String deleteTypes = "DROP TABLE LAB3_OBJECT_TYPES";
        final String deleteObjects = "DROP TABLE LAB3_OBJECTS";
        final String deleteAttributes = "DROP TABLE LAB3_ATTRIBUTES";
        final String deleteParameters = "DROP TABLE LAB3_PARAMETERS";
        final String deleteReferences = "DROP TABLE LAB3_REFERENCES";

        final String createTypes = "CREATE table LAB3_OBJECT_TYPES (\"ID\"  NUMBER(10) NOT NULL, \"NAME\" VARCHAR2(30) NOT NULL )";
        final String createObjects = "CREATE table LAB3_OBJECTS (\"ID\"  NUMBER(10) NOT NULL, \"NAME\" VARCHAR2(30) NOT NULL, \"TYPE\" NUMBER(10) NOT NULL)";
        final String createAttributes = "CREATE table LAB3_ATTRIBUTES (\"ID\"  NUMBER(10) NOT NULL, \"NAME\" VARCHAR2(30) NOT NULL, \"TYPE\"  NUMBER(10) NOT NULL)";
        final String createParameters = "CREATE table LAB3_PARAMS (\"OBJECT_ID\"  NUMBER(10) NOT NULL, \"ATTRIBUTE_ID\" NUMBER(10) NOT NULL, \"VALUE\" VARCHAR2(30) NOT NULL)";
        final String createReferences = "CREATE table LAB3_REFERENCES (\"OBJECT_ID\"  NUMBER(10) NOT NULL, \"REF_OBJECT_ID\" NUMBER(10) NOT NULL, \"NAME\" VARCHAR2(30) NOT NULL)";


//        initializer.update(deleteTypes);
//        initializer.update(deleteObjects);
//        initializer.update(deleteAttributes);
//        initializer.update(deleteParameters);
//        initializer.update(deleteReferences);
//        initializer.update(createTypes);
//        initializer.update(createObjects);
//        initializer.update(createAttributes);
//        initializer.update(createParameters);
//        initializer.update(createReferences);
//
//
//        ObjectTypeRepository objTypeRepo = ctx.getBean(ObjectTypeRepository.class);
//        ObjectRepository objectRepo = ctx.getBean(ObjectRepository.class);
//        AttributeRepository attributeRepo = ctx.getBean(AttributeRepository.class);
//        ReferenceRepository referenceRepo = ctx.getBean(ReferenceRepository.class);
//
//        objTypeRepo.insert(new ObjectType(1, "Smartphone"));
//        objTypeRepo.insert(new ObjectType(2, "Headphones"));
//        objectRepo.insert(new Object(1, "Samsung", 1));
//        objectRepo.insert(new Object(2, "Samsung headphones", 2));
//        attributeRepo.insert(new Attribute(1, "Price", 1));
//        referenceRepo.insert(new Reference(2, 1, "accessories"));
//
//        System.out.println(objTypeRepo.countRows());
//        System.out.println(objectRepo.countRows());
//        System.out.println(attributeRepo.countRows());
//        System.out.println(referenceRepo.countRows());


    }
}
