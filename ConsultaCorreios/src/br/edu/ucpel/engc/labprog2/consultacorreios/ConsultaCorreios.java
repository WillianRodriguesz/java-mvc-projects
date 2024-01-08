package br.edu.ucpel.engc.labprog2.consultacorreios;

import br.edu.ucpel.engc.labprog2.consultacorreios.ws.CResultado;
import java.math.BigDecimal;

/**
 *
 * @author mertins
 */
public class ConsultaCorreios {

    /**
     * http://www.correios.com.br/para-sua-empresa/servicos-para-o-seu-contrato/precos-e-prazos/calculador-remoto-de-precos-e-prazos
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CResultado calcPreco = ConsultaCorreios.calcPreco("", "", "41106", "96020690", "01505010", "1", 1, new BigDecimal(10), new BigDecimal(20), new BigDecimal(20), new BigDecimal(10), "S", new BigDecimal(10), "N");
        
        System.out.println(calcPreco);
    }
//
//    private static CResultado calcPreco(java.lang.String nCdEmpresa, java.lang.String sDsSenha, java.lang.String nCdServico, java.lang.String sCepOrigem, 
//            java.lang.String sCepDestino, java.lang.String nVlPeso, java.lang.String nCdFormato, java.lang.String nVlComprimento, java.lang.String nVlAltura, java.lang.String nVlLargura, java.lang.String nVlDiametro, java.lang.String sCdMaoPropria, java.lang.String nVlValorDeclarado, java.lang.String sCdAvisoRecebimento) {
//        br.edu.ucpel.engc.labprog2.consultacorreios.ws.CalcPrecoPrazoWS service = new br.edu.ucpel.engc.labprog2.consultacorreios.ws.CalcPrecoPrazoWS();
//        br.edu.ucpel.engc.labprog2.consultacorreios.ws.CalcPrecoPrazoWSHttpGet port = service.getCalcPrecoPrazoWSHttpGet();
//        return port.calcPreco(nCdEmpresa, sDsSenha, nCdServico, sCepOrigem, sCepDestino, nVlPeso, nCdFormato, nVlComprimento, nVlAltura, nVlLargura, nVlDiametro, sCdMaoPropria, nVlValorDeclarado, sCdAvisoRecebimento);
//    }

    private static CResultado calcPreco(java.lang.String nCdEmpresa, java.lang.String sDsSenha, java.lang.String nCdServico, java.lang.String sCepOrigem, java.lang.String sCepDestino, java.lang.String nVlPeso,
            int nCdFormato, java.math.BigDecimal nVlComprimento, java.math.BigDecimal nVlAltura, java.math.BigDecimal nVlLargura, java.math.BigDecimal nVlDiametro, java.lang.String sCdMaoPropria, java.math.BigDecimal nVlValorDeclarado, java.lang.String sCdAvisoRecebimento) {
        br.edu.ucpel.engc.labprog2.consultacorreios.ws.CalcPrecoPrazoWS service = new br.edu.ucpel.engc.labprog2.consultacorreios.ws.CalcPrecoPrazoWS();
        br.edu.ucpel.engc.labprog2.consultacorreios.ws.CalcPrecoPrazoWSSoap port = service.getCalcPrecoPrazoWSSoap();
        return port.calcPreco(nCdEmpresa, sDsSenha, nCdServico, sCepOrigem, sCepDestino, nVlPeso, nCdFormato, nVlComprimento, nVlAltura, nVlLargura, nVlDiametro, sCdMaoPropria, nVlValorDeclarado, sCdAvisoRecebimento);
    }

}
