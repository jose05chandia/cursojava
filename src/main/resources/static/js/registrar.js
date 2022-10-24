$(document).ready(function() {

});


async function registrarUsuario(){

    let datos={};
    let repite=document.getElementById("textRepeatPassword").value;
    datos.nombre=document.getElementById("textNombre").value;
    datos.apellido=document.getElementById("textApellido").value;
    datos.email=document.getElementById("textEmail").value;
    datos.password=document.getElementById("textPassword").value;
    if(datos.password!=repite){
        alert("La constraseña no es igual");
        return;
    }

    const request=await fetch('usuario',{
        method:"POST",
        headers:{
            'Accept':'application/json',
            'Content-Type':'application/json'
        },
        body:JSON.stringify(datos)
    });


    alert("Cuenta creada")
    window.location.href="login.html";
}
