package project.entity;

import javax.persistence.*;
import java.security.PublicKey;

@Entity
public class TransactionEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "hash")
    private String hash;

    @Lob
    @Column(name = "user_public_key")
    private PublicKey user;

    @Column(name = "product_id")
    private long productId;

    @ManyToOne
    @JoinColumn(name = "value")
    private FeedbackEntity value;

    @Lob
    @Column(name = "signature")
    private byte[] signature;

    @ManyToOne
    @JoinColumn(name = "block_entity")
    private BlockEntity blockEntity;

    public TransactionEntity(String hash, PublicKey user, long productId, FeedbackEntity value, byte[] signature,
                             BlockEntity blockEntity) {
        this.hash = hash;
        this.user = user;
        this.productId = productId;
        this.value = value;
        this.signature = signature;
        this.blockEntity = blockEntity;
    }

    public TransactionEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public PublicKey getUser() {
        return user;
    }

    public void setUser(PublicKey user) {
        this.user = user;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public FeedbackEntity getValue() {
        return value;
    }

    public void setValue(FeedbackEntity value) {
        this.value = value;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public BlockEntity getBlockEntity() {
        return blockEntity;
    }

    public void setBlockEntity(BlockEntity blockEntity) {
        this.blockEntity = blockEntity;
    }
}