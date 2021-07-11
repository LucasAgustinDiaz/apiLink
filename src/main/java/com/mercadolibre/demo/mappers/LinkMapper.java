package com.mercadolibre.demo.mappers;

import com.mercadolibre.demo.dtos.responses.ResponseIdLinkIdDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkMapper {
    private String link;
    private String link_id;
    private List<String> passwords;
    private Boolean active;
    private Integer redirects;



    public ResponseIdLinkIdDTO constructResponseId(LinkMapper linkMapper, String password){
        return new ResponseIdLinkIdDTO(linkMapper.getLink_id(),password);
    }

    public boolean passwordMatch(String password){
        return this.getPasswords().contains(password);
    }
}

/*
Crear un link: Endpoint POST para crear link a partir de una URL válida y
tiene que devolver un JSON con el linkId para utilizar en la redireccion.

request:
URL valida
www.google.com

response:
JSON ->  {
    linkId : google_id
}

Redirección:  Dado un link (ej: http://localhost:8080/link/{linkId} ) tiene que realizar un
 redirect a la URL enmascarada. Siempre y cuando el link sea válido. En el caso de que el link
 sea invalido devolver 404. (INVESTIGAR REDIRECT)



Estadísticas por link: Enpoint GET que dado un link (ej: http://localhost:8080/metrics/{linkID} )
 tiene que devolver la estadística de cantidad de veces que se redireccionó.

Invalidate link: Endpoint POST para invalidar un link ((ej:
http://localhost:8080/invalidate/{linkID} )
Al crear los links se tiene que poder agregar un password que va a ser un query param al
llamar a la redirección

 */
