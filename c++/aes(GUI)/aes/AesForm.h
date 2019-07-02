#pragma once

#include <msclr\marshal_cppstd.h>
#include <exception>
#include <string>
#include "AesFunctions.h"

namespace aes {
	using namespace System;
	using namespace System::ComponentModel;
	using namespace System::Collections;
	using namespace System::Windows::Forms;
	using namespace System::Data;
	using namespace System::Drawing;

	/// <summary>
	/// Summary for MyForm
	/// </summary>
	public ref class AesForm : public System::Windows::Forms::Form
	{
	public:
		AesForm(void)
		{
			InitializeComponent();
			//
			//TODO: Add the constructor code here
			//
		}

	protected:
		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		~AesForm()
		{
			if (components)
			{
				delete components;
			}
		}

	protected:

	private: System::Windows::Forms::RichTextBox^  DebugRichTextBox;
	private: System::Windows::Forms::Label^  OutputTextHexLabel;
	private: System::Windows::Forms::Label^  OutputTextStringLabel;
	private: System::Windows::Forms::LinkLabel^  CopyOutputHexTextLinkLabel;
	private: System::Windows::Forms::LinkLabel^  CopyOutputStringTextLinkLabel;


	private: System::Windows::Forms::Label^  InputTextLabel;
	private: System::Windows::Forms::Label^  KeyLabel;
	private: System::Windows::Forms::RadioButton^  InputAsStringRadioButton;
	private: System::Windows::Forms::RadioButton^  InputAsHexRadioButton;
	private: System::Windows::Forms::RadioButton^  KeyAsHexRadioButton;
	private: System::Windows::Forms::RadioButton^  KeyAsStringRadioButton;
	private: System::Windows::Forms::Label^  label1;
	private: System::Windows::Forms::Label^  DebugLabel;
	private: System::Windows::Forms::Button^  ExecuteButton;
	private: System::Windows::Forms::RadioButton^  EncryptRadioButton;
	private: System::Windows::Forms::RadioButton^  DecryptRadioButton;



	private: System::Windows::Forms::RichTextBox^  OutputTextStringTextBox;

	private: System::Windows::Forms::RichTextBox^  OutputTextHexTextBox;

	private: System::Windows::Forms::TextBox^  KeyTextBox;
	private: System::Windows::Forms::Label^  InfoLabel;
	private: msclr::interop::marshal_context context;
	private: System::Windows::Forms::RichTextBox^  InputTextBox;



	private:
		/// <summary>
		/// Required designer variable.
		/// </summary>
		System::ComponentModel::Container ^components;

#pragma region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		void InitializeComponent(void)
		{
			this->DebugRichTextBox = (gcnew System::Windows::Forms::RichTextBox());
			this->OutputTextHexLabel = (gcnew System::Windows::Forms::Label());
			this->OutputTextStringLabel = (gcnew System::Windows::Forms::Label());
			this->CopyOutputHexTextLinkLabel = (gcnew System::Windows::Forms::LinkLabel());
			this->CopyOutputStringTextLinkLabel = (gcnew System::Windows::Forms::LinkLabel());
			this->InputTextLabel = (gcnew System::Windows::Forms::Label());
			this->KeyLabel = (gcnew System::Windows::Forms::Label());
			this->InputAsStringRadioButton = (gcnew System::Windows::Forms::RadioButton());
			this->InputAsHexRadioButton = (gcnew System::Windows::Forms::RadioButton());
			this->KeyAsHexRadioButton = (gcnew System::Windows::Forms::RadioButton());
			this->KeyAsStringRadioButton = (gcnew System::Windows::Forms::RadioButton());
			this->label1 = (gcnew System::Windows::Forms::Label());
			this->DebugLabel = (gcnew System::Windows::Forms::Label());
			this->ExecuteButton = (gcnew System::Windows::Forms::Button());
			this->EncryptRadioButton = (gcnew System::Windows::Forms::RadioButton());
			this->DecryptRadioButton = (gcnew System::Windows::Forms::RadioButton());
			this->OutputTextStringTextBox = (gcnew System::Windows::Forms::RichTextBox());
			this->OutputTextHexTextBox = (gcnew System::Windows::Forms::RichTextBox());
			this->KeyTextBox = (gcnew System::Windows::Forms::TextBox());
			this->InfoLabel = (gcnew System::Windows::Forms::Label());
			this->InputTextBox = (gcnew System::Windows::Forms::RichTextBox());
			this->SuspendLayout();
			// 
			// DebugRichTextBox
			// 
			this->DebugRichTextBox->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->DebugRichTextBox->Location = System::Drawing::Point(12, 233);
			this->DebugRichTextBox->Name = L"DebugRichTextBox";
			this->DebugRichTextBox->ReadOnly = true;
			this->DebugRichTextBox->Size = System::Drawing::Size(787, 247);
			this->DebugRichTextBox->TabIndex = 5;
			this->DebugRichTextBox->Text = L"";
			this->DebugRichTextBox->TextChanged += gcnew System::EventHandler(this, &AesForm::DebugRichTextBox_TextChanged);
			// 
			// OutputTextHexLabel
			// 
			this->OutputTextHexLabel->AutoSize = true;
			this->OutputTextHexLabel->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 9, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->OutputTextHexLabel->Location = System::Drawing::Point(9, 159);
			this->OutputTextHexLabel->Name = L"OutputTextHexLabel";
			this->OutputTextHexLabel->Size = System::Drawing::Size(105, 15);
			this->OutputTextHexLabel->TabIndex = 3;
			this->OutputTextHexLabel->Text = L"Output Text (Hex):";
			// 
			// OutputTextStringLabel
			// 
			this->OutputTextStringLabel->AutoSize = true;
			this->OutputTextStringLabel->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 9, System::Drawing::FontStyle::Regular,
				System::Drawing::GraphicsUnit::Point, static_cast<System::Byte>(0)));
			this->OutputTextStringLabel->Location = System::Drawing::Point(9, 187);
			this->OutputTextStringLabel->Name = L"OutputTextStringLabel";
			this->OutputTextStringLabel->Size = System::Drawing::Size(115, 15);
			this->OutputTextStringLabel->TabIndex = 4;
			this->OutputTextStringLabel->Text = L"Output Text (String):";
			// 
			// CopyOutputHexTextLinkLabel
			// 
			this->CopyOutputHexTextLinkLabel->AutoSize = true;
			this->CopyOutputHexTextLinkLabel->Location = System::Drawing::Point(769, 161);
			this->CopyOutputHexTextLinkLabel->Name = L"CopyOutputHexTextLinkLabel";
			this->CopyOutputHexTextLinkLabel->Size = System::Drawing::Size(31, 13);
			this->CopyOutputHexTextLinkLabel->TabIndex = 5;
			this->CopyOutputHexTextLinkLabel->TabStop = true;
			this->CopyOutputHexTextLinkLabel->Text = L"Copy";
			this->CopyOutputHexTextLinkLabel->LinkClicked += gcnew System::Windows::Forms::LinkLabelLinkClickedEventHandler(this, &AesForm::CopyOutputHexTextLinkLabel_LinkClicked);
			// 
			// CopyOutputStringTextLinkLabel
			// 
			this->CopyOutputStringTextLinkLabel->AutoSize = true;
			this->CopyOutputStringTextLinkLabel->Location = System::Drawing::Point(769, 187);
			this->CopyOutputStringTextLinkLabel->Name = L"CopyOutputStringTextLinkLabel";
			this->CopyOutputStringTextLinkLabel->Size = System::Drawing::Size(31, 13);
			this->CopyOutputStringTextLinkLabel->TabIndex = 6;
			this->CopyOutputStringTextLinkLabel->TabStop = true;
			this->CopyOutputStringTextLinkLabel->Text = L"Copy";
			this->CopyOutputStringTextLinkLabel->LinkClicked += gcnew System::Windows::Forms::LinkLabelLinkClickedEventHandler(this, &AesForm::CopyOutputStringTextLinkLabel_LinkClicked);
			// 
			// InputTextLabel
			// 
			this->InputTextLabel->AutoSize = true;
			this->InputTextLabel->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 9, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->InputTextLabel->Location = System::Drawing::Point(9, 41);
			this->InputTextLabel->Name = L"InputTextLabel";
			this->InputTextLabel->Size = System::Drawing::Size(37, 15);
			this->InputTextLabel->TabIndex = 9;
			this->InputTextLabel->Text = L"Input:";
			// 
			// KeyLabel
			// 
			this->KeyLabel->AutoSize = true;
			this->KeyLabel->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 9, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->KeyLabel->Location = System::Drawing::Point(9, 65);
			this->KeyLabel->Name = L"KeyLabel";
			this->KeyLabel->Size = System::Drawing::Size(30, 15);
			this->KeyLabel->TabIndex = 10;
			this->KeyLabel->Text = L"Key:";
			// 
			// InputAsStringRadioButton
			// 
			this->InputAsStringRadioButton->AutoCheck = false;
			this->InputAsStringRadioButton->AutoSize = true;
			this->InputAsStringRadioButton->Checked = true;
			this->InputAsStringRadioButton->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 9, System::Drawing::FontStyle::Regular,
				System::Drawing::GraphicsUnit::Point, static_cast<System::Byte>(0)));
			this->InputAsStringRadioButton->Location = System::Drawing::Point(638, 39);
			this->InputAsStringRadioButton->Name = L"InputAsStringRadioButton";
			this->InputAsStringRadioButton->Size = System::Drawing::Size(57, 19);
			this->InputAsStringRadioButton->TabIndex = 11;
			this->InputAsStringRadioButton->TabStop = true;
			this->InputAsStringRadioButton->Text = L"String";
			this->InputAsStringRadioButton->UseVisualStyleBackColor = true;
			this->InputAsStringRadioButton->Click += gcnew System::EventHandler(this, &AesForm::InputAsStringRadioButton_Click);
			// 
			// InputAsHexRadioButton
			// 
			this->InputAsHexRadioButton->AutoCheck = false;
			this->InputAsHexRadioButton->AutoSize = true;
			this->InputAsHexRadioButton->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 9, System::Drawing::FontStyle::Regular,
				System::Drawing::GraphicsUnit::Point, static_cast<System::Byte>(0)));
			this->InputAsHexRadioButton->Location = System::Drawing::Point(701, 39);
			this->InputAsHexRadioButton->Name = L"InputAsHexRadioButton";
			this->InputAsHexRadioButton->Size = System::Drawing::Size(98, 19);
			this->InputAsHexRadioButton->TabIndex = 12;
			this->InputAsHexRadioButton->TabStop = true;
			this->InputAsHexRadioButton->Text = L"Hexadecimal";
			this->InputAsHexRadioButton->UseVisualStyleBackColor = true;
			this->InputAsHexRadioButton->Click += gcnew System::EventHandler(this, &AesForm::InputAsHexRadioButton_Click);
			// 
			// KeyAsHexRadioButton
			// 
			this->KeyAsHexRadioButton->AutoCheck = false;
			this->KeyAsHexRadioButton->AutoSize = true;
			this->KeyAsHexRadioButton->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 9, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->KeyAsHexRadioButton->Location = System::Drawing::Point(701, 65);
			this->KeyAsHexRadioButton->Name = L"KeyAsHexRadioButton";
			this->KeyAsHexRadioButton->Size = System::Drawing::Size(98, 19);
			this->KeyAsHexRadioButton->TabIndex = 14;
			this->KeyAsHexRadioButton->TabStop = true;
			this->KeyAsHexRadioButton->Text = L"Hexadecimal";
			this->KeyAsHexRadioButton->UseVisualStyleBackColor = true;
			this->KeyAsHexRadioButton->Click += gcnew System::EventHandler(this, &AesForm::KeyAsHexRadioButton_Click);
			// 
			// KeyAsStringRadioButton
			// 
			this->KeyAsStringRadioButton->AutoCheck = false;
			this->KeyAsStringRadioButton->AutoSize = true;
			this->KeyAsStringRadioButton->Checked = true;
			this->KeyAsStringRadioButton->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 9, System::Drawing::FontStyle::Regular,
				System::Drawing::GraphicsUnit::Point, static_cast<System::Byte>(0)));
			this->KeyAsStringRadioButton->Location = System::Drawing::Point(638, 65);
			this->KeyAsStringRadioButton->Name = L"KeyAsStringRadioButton";
			this->KeyAsStringRadioButton->Size = System::Drawing::Size(57, 19);
			this->KeyAsStringRadioButton->TabIndex = 13;
			this->KeyAsStringRadioButton->TabStop = true;
			this->KeyAsStringRadioButton->Text = L"String";
			this->KeyAsStringRadioButton->UseVisualStyleBackColor = true;
			this->KeyAsStringRadioButton->Click += gcnew System::EventHandler(this, &AesForm::KeyAsStringRadioButton_Click);
			// 
			// label1
			// 
			this->label1->AutoSize = true;
			this->label1->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 15.75F, System::Drawing::FontStyle::Bold, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->label1->Location = System::Drawing::Point(217, 7);
			this->label1->Name = L"label1";
			this->label1->Size = System::Drawing::Size(387, 25);
			this->label1->TabIndex = 15;
			this->label1->Text = L"AES Encryptor/Decryptor using C++";
			// 
			// DebugLabel
			// 
			this->DebugLabel->AutoSize = true;
			this->DebugLabel->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 9, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->DebugLabel->Location = System::Drawing::Point(12, 217);
			this->DebugLabel->Name = L"DebugLabel";
			this->DebugLabel->Size = System::Drawing::Size(86, 15);
			this->DebugLabel->TabIndex = 16;
			this->DebugLabel->Text = L"Debug Output:";
			// 
			// ExecuteButton
			// 
			this->ExecuteButton->Location = System::Drawing::Point(12, 120);
			this->ExecuteButton->Name = L"ExecuteButton";
			this->ExecuteButton->Size = System::Drawing::Size(787, 23);
			this->ExecuteButton->TabIndex = 2;
			this->ExecuteButton->Text = L"Execute";
			this->ExecuteButton->UseVisualStyleBackColor = true;
			this->ExecuteButton->Click += gcnew System::EventHandler(this, &AesForm::ExecuteButton_Click);
			// 
			// EncryptRadioButton
			// 
			this->EncryptRadioButton->AutoCheck = false;
			this->EncryptRadioButton->Checked = true;
			this->EncryptRadioButton->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 9, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->EncryptRadioButton->Location = System::Drawing::Point(321, 93);
			this->EncryptRadioButton->Name = L"EncryptRadioButton";
			this->EncryptRadioButton->Size = System::Drawing::Size(78, 21);
			this->EncryptRadioButton->TabIndex = 18;
			this->EncryptRadioButton->TabStop = true;
			this->EncryptRadioButton->Text = L"Encrypt";
			this->EncryptRadioButton->UseVisualStyleBackColor = true;
			this->EncryptRadioButton->Click += gcnew System::EventHandler(this, &AesForm::EncryptRadioButton_Click);
			// 
			// DecryptRadioButton
			// 
			this->DecryptRadioButton->AutoCheck = false;
			this->DecryptRadioButton->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 9, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->DecryptRadioButton->Location = System::Drawing::Point(405, 93);
			this->DecryptRadioButton->Name = L"DecryptRadioButton";
			this->DecryptRadioButton->Size = System::Drawing::Size(68, 21);
			this->DecryptRadioButton->TabIndex = 19;
			this->DecryptRadioButton->TabStop = true;
			this->DecryptRadioButton->Text = L"Decrypt";
			this->DecryptRadioButton->UseVisualStyleBackColor = true;
			this->DecryptRadioButton->Click += gcnew System::EventHandler(this, &AesForm::DecryptRadioButton_Click);
			// 
			// OutputTextStringTextBox
			// 
			this->OutputTextStringTextBox->AcceptsTab = true;
			this->OutputTextStringTextBox->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Regular,
				System::Drawing::GraphicsUnit::Point, static_cast<System::Byte>(0)));
			this->OutputTextStringTextBox->Location = System::Drawing::Point(131, 184);
			this->OutputTextStringTextBox->Name = L"OutputTextStringTextBox";
			this->OutputTextStringTextBox->ReadOnly = true;
			this->OutputTextStringTextBox->Size = System::Drawing::Size(632, 26);
			this->OutputTextStringTextBox->TabIndex = 26;
			this->OutputTextStringTextBox->Text = L"";
			this->OutputTextStringTextBox->WordWrap = false;
			// 
			// OutputTextHexTextBox
			// 
			this->OutputTextHexTextBox->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Regular,
				System::Drawing::GraphicsUnit::Point, static_cast<System::Byte>(0)));
			this->OutputTextHexTextBox->Location = System::Drawing::Point(131, 158);
			this->OutputTextHexTextBox->Name = L"OutputTextHexTextBox";
			this->OutputTextHexTextBox->ReadOnly = true;
			this->OutputTextHexTextBox->Size = System::Drawing::Size(632, 26);
			this->OutputTextHexTextBox->TabIndex = 25;
			this->OutputTextHexTextBox->Text = L"";
			// 
			// KeyTextBox
			// 
			this->KeyTextBox->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->KeyTextBox->Location = System::Drawing::Point(49, 61);
			this->KeyTextBox->Name = L"KeyTextBox";
			this->KeyTextBox->Size = System::Drawing::Size(583, 26);
			this->KeyTextBox->TabIndex = 1;
			// 
			// InfoLabel
			// 
			this->InfoLabel->AutoSize = true;
			this->InfoLabel->Location = System::Drawing::Point(318, 499);
			this->InfoLabel->Name = L"InfoLabel";
			this->InfoLabel->Size = System::Drawing::Size(168, 26);
			this->InfoLabel->TabIndex = 27;
			this->InfoLabel->Text = L"Created by Jose A. Alvarado\r\nCopyright J.A.A. Productions 2019";
			this->InfoLabel->TextAlign = System::Drawing::ContentAlignment::MiddleCenter;
			// 
			// InputTextBox
			// 
			this->InputTextBox->AcceptsTab = true;
			this->InputTextBox->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->InputTextBox->Location = System::Drawing::Point(49, 35);
			this->InputTextBox->Name = L"InputTextBox";
			this->InputTextBox->Size = System::Drawing::Size(583, 26);
			this->InputTextBox->TabIndex = 0;
			this->InputTextBox->Text = L"";
			this->InputTextBox->WordWrap = false;
			// 
			// AesForm
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(6, 13);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->ClientSize = System::Drawing::Size(811, 534);
			this->Controls->Add(this->InputTextBox);
			this->Controls->Add(this->InfoLabel);
			this->Controls->Add(this->KeyTextBox);
			this->Controls->Add(this->OutputTextHexTextBox);
			this->Controls->Add(this->OutputTextStringTextBox);
			this->Controls->Add(this->DecryptRadioButton);
			this->Controls->Add(this->EncryptRadioButton);
			this->Controls->Add(this->ExecuteButton);
			this->Controls->Add(this->DebugLabel);
			this->Controls->Add(this->label1);
			this->Controls->Add(this->KeyAsHexRadioButton);
			this->Controls->Add(this->KeyAsStringRadioButton);
			this->Controls->Add(this->InputAsHexRadioButton);
			this->Controls->Add(this->InputAsStringRadioButton);
			this->Controls->Add(this->KeyLabel);
			this->Controls->Add(this->InputTextLabel);
			this->Controls->Add(this->CopyOutputStringTextLinkLabel);
			this->Controls->Add(this->CopyOutputHexTextLinkLabel);
			this->Controls->Add(this->OutputTextStringLabel);
			this->Controls->Add(this->OutputTextHexLabel);
			this->Controls->Add(this->DebugRichTextBox);
			this->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 8.25F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->FormBorderStyle = System::Windows::Forms::FormBorderStyle::FixedDialog;
			this->MaximizeBox = false;
			this->Name = L"AesForm";
			this->Text = L"Aes";
			this->ResumeLayout(false);
			this->PerformLayout();

		}
#pragma endregion
	private: System::Void ExecuteButton_Click(System::Object^  sender, System::EventArgs^  e) {
		try {
			AesFunctions::TextMode inputTextMode = (InputAsStringRadioButton->Checked) ? AesFunctions::TextMode::STRING : AesFunctions::TextMode::HEX;
			AesFunctions::TextMode keyTextMode = (KeyAsStringRadioButton->Checked) ? AesFunctions::TextMode::STRING : AesFunctions::TextMode::HEX;
			AesFunctions::CipherMode cipherMode = (EncryptRadioButton->Checked) ? AesFunctions::CipherMode::ENCRYPT : AesFunctions::CipherMode::DECRYPT;

			std::string inputText = context.marshal_as<std::string>(InputTextBox->Text->ToString());
			std::string inputKey = context.marshal_as<std::string>(KeyTextBox->Text->ToString());

			std::string inputTextBin = AesFunctions::encryptDecryptInputTextPrep(inputText, cipherMode, inputTextMode);
			std::string keyTextBin = AesFunctions::keyTextPrep(inputKey, keyTextMode);

			String^ completionMessage = "";
			std::pair<std::string, std::string> outputTextHexStringPair;

			switch (cipherMode)
			{
			case AesFunctions::CipherMode::ENCRYPT:
				AesFunctions::encrypt(inputTextBin, keyTextBin, outputTextHexStringPair);
				completionMessage = "The PlainText has been sucessfully Encrypted!";
				break;
			case AesFunctions::CipherMode::DECRYPT:
				AesFunctions::decrypt(inputTextBin, keyTextBin, outputTextHexStringPair);
				completionMessage = "The CipherText has been sucessfully Decrypted!";
				break;
			default:
				throw std::exception("An unexpected error has occured!");
				break;
			}

			if (outputTextHexStringPair.first == "" || outputTextHexStringPair.second == "")
				throw std::exception("Please enter inputs!");

			String^ outputTextHex = context.marshal_as<String^>(outputTextHexStringPair.first);
			String^ outputTextStr = context.marshal_as<String^>(outputTextHexStringPair.second);
			OutputTextHexTextBox->Text = outputTextHex;
			OutputTextStringTextBox->Text = outputTextStr;

			String^ debugStr = context.marshal_as<String^>(AesFunctions::outputStream.str());
			DebugRichTextBox->Clear();
			DebugRichTextBox->Text = debugStr;
			AesFunctions::outputStream.str(std::string());

			MessageBox::Show(completionMessage, "Complete!", MessageBoxButtons::OK, MessageBoxIcon::Information);
		}
		catch (std::exception e)
		{
			String^ errMsg = context.marshal_as<String^>(e.what());
			MessageBox::Show(errMsg, "Error!", MessageBoxButtons::OK, MessageBoxIcon::Error);
		}
	}
	private: System::Void InputAsStringRadioButton_Click(System::Object^  sender, System::EventArgs^  e) {
		InputAsStringRadioButton->Checked = true;
		InputAsHexRadioButton->Checked = false;
	}
	private: System::Void InputAsHexRadioButton_Click(System::Object^  sender, System::EventArgs^  e) {
		InputAsStringRadioButton->Checked = false;
		InputAsHexRadioButton->Checked = true;
	}
	private: System::Void KeyAsStringRadioButton_Click(System::Object^  sender, System::EventArgs^  e) {
		KeyAsStringRadioButton->Checked = true;
		KeyAsHexRadioButton->Checked = false;
	}
	private: System::Void KeyAsHexRadioButton_Click(System::Object^  sender, System::EventArgs^  e) {
		KeyAsStringRadioButton->Checked = false;
		KeyAsHexRadioButton->Checked = true;
	}
	private: System::Void EncryptRadioButton_Click(System::Object^  sender, System::EventArgs^  e) {
		EncryptRadioButton->Checked = true;
		DecryptRadioButton->Checked = false;
	}
	private: System::Void DecryptRadioButton_Click(System::Object^  sender, System::EventArgs^  e) {
		EncryptRadioButton->Checked = false;
		DecryptRadioButton->Checked = true;
	}
	private: System::Void CopyOutputHexTextLinkLabel_LinkClicked(System::Object^  sender, System::Windows::Forms::LinkLabelLinkClickedEventArgs^  e) {
		String^ outputText = OutputTextHexTextBox->Text;
		System::Windows::Forms::Clipboard::SetText(outputText);

		MessageBox::Show("Text Copied!", "Copied!", MessageBoxButtons::OK, MessageBoxIcon::Information);
	}
	private: System::Void CopyOutputStringTextLinkLabel_LinkClicked(System::Object^  sender, System::Windows::Forms::LinkLabelLinkClickedEventArgs^  e) {
		String^ outputText = OutputTextStringTextBox->Text;
		System::Windows::Forms::Clipboard::SetText(outputText);

		MessageBox::Show("Text Copied!", "Copied!", MessageBoxButtons::OK, MessageBoxIcon::Information);
	}
	private: System::Void DebugRichTextBox_TextChanged(System::Object^  sender, System::EventArgs^  e) {
		DebugRichTextBox->SelectionStart = DebugRichTextBox->Text->Length;
		DebugRichTextBox->ScrollToCaret();
	}
	};
}
