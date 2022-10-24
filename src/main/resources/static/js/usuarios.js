$(document).ready(function() {
cargarUsuarios();
  $('#usuarios').DataTable();
});

function getHeaders(){
    return {
             'Accept':'application/json',
             'Content-Type':'application/json',
             'Authorization':localStorage.token
           };
}
async function cargarUsuarios(){
    const request=await fetch('usuarios',{
        method:"GET",
        headers:getHeaders()
    });
    const usuarios=await request.json();
    console.log(usuarios);
    let contenidoHtml=' ';
    for(let item of usuarios){
        let button='<a onClick="deleteUser('+item.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
        let row=    '<tr>'
                +'<td>'+item.id+'</td>'
                +'<td>'+item.nombre+'</td>'
                +'<td>'+item.apellido+'</td>'
                +'<td>'+item.email+'</td>'
                +'<td>'+item.password+'</td>'
                +'<td>'+button+'</td>'
            +'</tr>';
        contenidoHtml+=row;
    }
    document.querySelector('#usuarios tbody').outerHTML=contenidoHtml;
}


async function deleteUser(id){
    if(!confirm("¿Deseas eliminar el usuario?")){
        return;
    }
     const request=await fetch('usuario/'+id,{
            method:"DELETE",
            headers:getHeaders()
        });
        location.reload();
}