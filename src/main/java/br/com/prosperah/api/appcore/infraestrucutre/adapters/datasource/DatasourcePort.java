package br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource;

import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model.CadastralUserPersistData;
import org.apache.coyote.BadRequestException;

import java.util.Optional;

public interface DatasourcePort {

    Optional<CadastralUserPersistData> saveCadastralUser(CadastralUserPersistData cadUser) throws BadRequestException;
}
