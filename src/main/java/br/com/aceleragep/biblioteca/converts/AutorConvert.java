package br.com.aceleragep.biblioteca.converts;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.aceleragep.biblioteca.dto.inputs.AutorInput;
import br.com.aceleragep.biblioteca.dto.outputs.AutorOutput;
import br.com.aceleragep.biblioteca.entities.AutorEntity;

@Component
public class AutorConvert {

	@Autowired
	private ModelMapper modelMapper;

	public AutorEntity inputToNewEntity(@Valid AutorInput input) {
		return modelMapper.map(input, AutorEntity.class);
	}

	public void copyInputToEntity(AutorInput input, AutorEntity autorEncontrado) {
		modelMapper.map(input, autorEncontrado);
	}

	public AutorOutput entityToOutput(AutorEntity autorEntity) {
		return modelMapper.map(autorEntity, AutorOutput.class);
	}

	public Page<AutorOutput> ListEntityToPageOutput(Page<AutorEntity> autores) {
		return autores.map(this::entityToOutput);
	}

}
