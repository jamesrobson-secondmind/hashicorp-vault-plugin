package com.datapipe.jenkins.vault.credentials.snapshots;


import com.cloudbees.plugins.credentials.CredentialsSnapshotTaker;
import com.datapipe.jenkins.vault.credentials.SecretSnapshot;
import com.datapipe.jenkins.vault.credentials.common.VaultSSHUserPrivateKey;
import com.datapipe.jenkins.vault.credentials.common.VaultSSHUserPrivateKeyImpl;
import hudson.util.Secret;
import java.util.logging.Logger;


public class VaultSSHUserPrivateKeyCredentialsSnapshotTaker extends CredentialsSnapshotTaker<VaultSSHUserPrivateKey> {
    private static final Logger LOGGER = Logger.getLogger(VaultSSHUserPrivateKeyCredentialsSnapshotTaker.class.getName());

    @Override
    public Class<VaultSSHUserPrivateKey> type() {
        return VaultSSHUserPrivateKey.class;
    }

    @Override
    public VaultSSHUserPrivateKey snapshot(
        VaultSSHUserPrivateKey credential) {
            LOGGER.warning("took snapshot of ssh key");
        SecretSnapshot passphrase = new SecretSnapshot(credential.getPassphrase());
        SecretSnapshot privateKey = new SecretSnapshot(Secret.fromString(credential.getPrivateKeyKey()));
        SecretSnapshot username = new SecretSnapshot(Secret.fromString(credential.getUsernameKey()));
        return new VaultSSHUserPrivateKeyImpl(credential.getScope(), credential.getId(), credential.getDescription(), username, privateKey, passphrase);
    }
}
