package br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.repository;

import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model.WalletPersistData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<WalletPersistData, Long> {

    Optional<WalletPersistData> findByUserId(byte[] walletId);


    @Modifying
    @Transactional
    @Query("UPDATE WalletPersistData w SET w.fixedAssetsPatrimony = :fixedAssetsPatrimony WHERE w.id = :walletId")
    void updateWalletPatrimony (byte[] walletId, double fixedAssetsPatrimony);
}
