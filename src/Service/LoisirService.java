/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Core.DataSource;
import IService.ILoisirService;
import java.sql.Connection;

/**
 *
 * @author hero
 */
public class LoisirService implements ILoisirService {
    private Connection con = DataSource.getInstance().getCon();
}
