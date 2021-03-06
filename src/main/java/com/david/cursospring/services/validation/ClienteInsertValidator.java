package com.david.cursospring.services.validation;

import com.david.cursospring.domain.Cliente;
import com.david.cursospring.domain.enums.TipoCliente;
import com.david.cursospring.dto.ClienteNewDTO;
import com.david.cursospring.repositories.ClienteRepository;
import com.david.cursospring.resources.exceptions.FieldMessage;
import com.david.cursospring.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    private ClienteRepository repo;

    @Override
    public void initialize(ClienteInsert ann) {

    }

    @Override
    public boolean isValid(ClienteNewDTO o, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        //insere os erros na lista
        if(o.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(o.getCpfCnpj())) {
            list.add(new FieldMessage("cpfCnpj", "CPF inválido"));
        }

        if(o.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(o.getCpfCnpj())) {
            list.add(new FieldMessage("cpfCnpj", "CNPJ inválido"));
        }

        Cliente aux = repo.findByEmail(o.getEmail());

        if (aux != null) {
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
