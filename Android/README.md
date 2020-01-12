# proyecto
Alcoholimpiadas proyect

Usuarios existentes y mensajes existentes en la base de datos:

{
  "Users" : {
    "Ft1WO4ch6jN7uuhV1mt9zZ2FkL63" : {
      "email" : "user02@hotmail.com",
      "name" : "user02"
      contraseña : user02
    },
    "UBxCNSgQEGOnL4NvOUVCZ9DjJUm1" : {
      "email" : "test03@gmail.com",
      "name" : "test03"
      contraseña: test03
    },
    "ZBwJQXw3eMThj0DvNz4J53Ogxzk2" : {
      "email" : "tester@teste.com",
      "name" : "tester"
      contraseña: tester
    }
  },
  "messages" : {
    "-LyR2Q0TYZUZAeKYRHnY" : {
      "message" : "user02 message",
      "name" : "user02"
    },
    "-LyR2YcJPtPuxtD-wQNt" : {
      "message" : "test03 mesaje",
      "name" : "test03"
    },
    "-LyR2gRfMMbksb2Q8D2h" : {
      "message" : "tester mensajeeee",
      "name" : "tester"
    }
  }
}

Se puede crear usuario con cualquier email.

Cuando un usuario es creado desde el movil, este lo recuerda y se logea automaticamente. Tendremos que darle a Sign Out para crear o entrar con otro usuario.

La base de datos es online en Firebase.

Hay un error que el ultimo mensaje mandado por otro usuario nos aparece como nuestro hasta que nosotros mandamos un mensaje, y despues se actualiza y aparece el nombre del propiertario del mensaje.

No se ha acabado de implementar las fotos.
Siempre se une al mismo grupo predefinido. El boton New Group no es funcional por ahora.