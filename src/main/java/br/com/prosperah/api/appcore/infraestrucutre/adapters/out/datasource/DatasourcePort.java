package br.com.prosperah.api.appcore.infraestrucutre.adapters.out.datasource;

import br.com.prosperah.api.appcore.domain.CadastralUser;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.out.datasource.model.CadastralUserPersistData;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.out.datasource.model.UserPersistData;
import org.apache.coyote.BadRequestException;

public interface DatasourcePort {

    boolean saveCadastralUser(CadastralUserPersistData cadUser) throws BadRequestException;
}
