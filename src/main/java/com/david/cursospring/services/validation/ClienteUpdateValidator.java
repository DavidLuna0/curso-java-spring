package com.david.cursospring.services.validation;

import com.david.cursospring.domain.Cliente;
import com.david.cursospring.domain.enums.TipoCliente;
import com.david.cursospring.dto.ClienteDTO;
import com.david.cursospring.dto.ClienteNewDTO;
import com.david.cursospring.repositories.ClienteRepository;
import com.david.cursospring.resources.exceptions.FieldMessage;
import com.david.cursospring.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClienteRepository repo;

    @Override
    public void initialize(ClienteUpdate ann) {

    }

    @Override
    public boolean isValid(ClienteDTO o, ConstraintValidatorContext context) {
        Map<String, String> map = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();
        Cliente aux = repo.findByEmail(o.getEmail());

        if (aux != null && !aux.getId().equals(uId)) {
            list.add(new FieldMessage("email", "Email já existente"));
        }

        //Percorre a lista de field message e adiciona na lista de erro do framework
        for(FieldMessage e : list) {
            context.disableDefaultConstraintViolation();;
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
