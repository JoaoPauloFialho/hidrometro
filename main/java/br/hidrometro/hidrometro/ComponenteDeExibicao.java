package main.java.br.hidrometro.hidrometro;

/**
 * Classe abstrata que representa um componente de exibição do hidrômetro.
 * <p>
 * Os componentes de exibição são responsáveis por mostrar ao usuário
 * algum aspecto da medição de volume (por exemplo, ponteiros ou odômetro).
 * 
 * <p>Ela define a estrutura comum a todos os componentes de exibição:
 * um atributo {@link #volume} e o método abstrato {@link #atualiza(float)}.
 *
 * <p><b>Didático:</b> Pense nesta classe como um "molde" para os mostradores
 * do hidrômetro. Cada tipo de mostrador (litros, decilitros, odômetro etc.)
 * vai herdar daqui e implementar sua forma específica de atualizar a leitura.
 */
public abstract class ComponenteDeExibicao {

    /**
     * Volume associado ao componente de exibição.
     * <p>
     * Representa o valor que será exibido pelo mostrador (em litros, decilitros ou acumulado).
     */
    protected float volume;

    /**
     * Atualiza o estado do componente de exibição com base no volume informado.
     *
     * @param volume Novo volume que deve ser refletido no componente.
     */
    public abstract void atualiza(float volume);
    
   
}
