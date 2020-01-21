<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Welcome extends CI_Controller {

	public function index()
	{

	    	$this->load->view('welcome_message');
	}
	public function getpcs()
	{
				   	$this->load->model('Pcdatabase');
						$myObj = $this->Pcdatabase->getPcs();
						$myJSON = json_encode($myObj);
						echo $myJSON;

	}
	public function getExcute($id,$lockedStatus,$time)
	{
			$this->load->model('Pcdatabase');
			$myObj = $this->Pcdatabase->getOnePc($id,$lockedStatus,$time);
			echo $myObj['excute'];
			$this->Pcdatabase->setExcute($id,"None");
	}
	public function setExcute($id,$excute)
	{
			$this->load->model('Pcdatabase');
			$this->Pcdatabase->setExcute($id,$excute);
	}
	public function resetDBValue()
	{
		$this->load->model('Pcdatabase');
		$this->Pcdatabase->resetValue();
	}
}
