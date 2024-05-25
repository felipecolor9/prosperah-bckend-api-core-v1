package br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource;

import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model.CadastralUserPersistData;
import org.apache.coyote.BadRequestException;

public interface DatasourcePort {

    boolean saveCadastralUser(CadastralUserPersistData cadUser) throws BadRequestException;
}
