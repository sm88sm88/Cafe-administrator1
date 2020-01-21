<?php
class Pcdatabase extends CI_Model {

    public function getPcs()
    {

      $query = $this->db->query('SELECT * FROM pc');
      $this->db->query("UPDATE `pc` SET `status` = 'Offline'");
      $i =0;
      $myObj;
          foreach ($query->result() as $row)
          {
            $myObj[$i]["id"] = $row->id;
            $myObj[$i]["status"] = $row->status;
            $myObj[$i]["excute"] = $row->excute;
            $myObj[$i]["price"] = $row->price;
            $i++;
          }
          return $myObj;
    }
    public function getOnePc($id,$lockedStatus,$time)
    {
      $query = $this->db->query("SELECT * FROM `pc`  WHERE `pc`.`id` = $id");
      $this->db->query("UPDATE `pc` SET `status` = 'Online $lockedStatus',`price` = $time WHERE `pc`.`id` = $id");
      $myObj  ;
      foreach ($query->result() as $row)
      {
            $myObj["id"] = $row->id;
            $myObj["status"] = $row->status;
            $myObj["excute"] = $row->excute;
            $myObj["price"] = $row->price;
          }
          return $myObj;
        }
    public function setExcute($id,$excute)
    {
      $this->db->query("UPDATE `pc` SET `excute` = '$excute' WHERE `pc`.`id` = $id");
    }
    public function resetValue()
    {
      $this->db->query("UPDATE `pc` SET `excute` = 'None' , `status` = 'Offline' ");
    }
}
?>
