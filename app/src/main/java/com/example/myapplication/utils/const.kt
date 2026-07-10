package com.example.myapplication.utils

import com.example.myapplication.R

const val TRIGO_FAIL = "TRIGO_PROCESS_FAILED"
const val MODULE_RUN_FAIL = "Modules run Error"
const val USER_DATA_NOT_FOUND = "User 0 not found"

//MAIN ACTIVITY AND FRAGMENT
const val MAIN_TAG = "MAIN_TAG"

//CONNECTION FRAGMENT
const val JOHN_DOE_ID = 0
const val USER_ID = 1

//CREATE USER FRAGMENT
const val CREATE_USER_TAG = "CREATE_USER_TAG"

//AQ Summary
const val AQ_SUMMARY_TAG = "AQ_SUMMARY_TAG"

//ML KIT Start
const val ML_KIT_START_TAG = "ML_KIT_START_TAG"

//ML KIT Run
const val ML_KIT_RUN_TAG = "ML_KIT_RUN_TAG"
const val ML_KIT_RELATIVE_PATH = "Pictures/TrigoMobile"
const val ML_KIT_IMG = "/capture_ia.jpg"

//Photo
const val PHOTO_TAG = "PHOTO_TAG"
const val NO_PHOTO_TAG = "NO_PHOTO_TAG"
/*
// AQ Creation
val listOfTextInputLayoutId: List<Int> = listOf(
    R.id.creation_aq_reference_til,
    R.id.creation_aq_numero_aq_til,
    R.id.creation_aq_numero_ncr_til,
    R.id.creation_aq_fouurnisseur_til,
    R.id.creation_aq_numro_lot_til,
    R.id.creation_aq_numro_bl_til,
    R.id.creation_aq_localisation_til,
    R.id.creation_aq_comment_til,
    R.id.creation_aq_description_probleme_til,
    R.id.creation_aq_qte_nok_til,
    R.id.creation_aq_qte_echantillon_til,
    R.id.creation_aq_qte_lot_til,
    R.id.creation_aq_standard_til,
    R.id.creation_aq_sn_til,
    R.id.creation_aq_datecode_til,
    R.id.creation_aq_qte_stock_til,
    R.id.creation_aq_pourquoi_til,
    R.id.creation_aq_consequences_til
)

val obligatoryTextFields: List<Int> = listOf(
    R.id.creation_aq_reference_til,
    R.id.creation_aq_numro_bl_til,
    R.id.creation_aq_fouurnisseur_til,
    R.id.creation_aq_numro_lot_til,
    R.id.creation_aq_qte_lot_til,
    R.id.creation_aq_qte_echantillon_til,
)

val listOfEdiTextId: List<Int> = listOf(
    R.id.creation_aq_reference,
    R.id.creation_aq_numero_aq,
    R.id.creation_aq_numero_ncr,
    R.id.creation_aq_fouurnisseur,
    R.id.creation_aq_numro_lot,
    R.id.creation_aq_numro_bl,
    R.id.creation_aq_localisation,
    R.id.creation_aq_comment,
    R.id.creation_aq_description_probleme,
    R.id.creation_aq_qte_nok,
    R.id.creation_aq_qte_echantillon,
    R.id.creation_aq_qte_lot,
    R.id.creation_aq_standard,
    R.id.creation_aq_sn,
    R.id.creation_aq_datecode,
    R.id.creation_aq_qte_stock,
    R.id.creation_aq_pourquoi,
    R.id.creation_aq_consequences
)
*/
const val IPC_DIM = "IPC_DIM"
const val DOC = "DOC"

// ViewPager
const val PHOTO_GENERAL = "Vue Globale"
const val PHOTO_DETAIL = "Vue de détail"
const val PHOTO_BLUE = "Vue sous lumière bleue"

const val PHOTO_GENERAL_NAME = "VG"
const val PHOTO_DETAIL_NAME = "VD"
const val PHOTO_BLUE_NAME = "VB"

const val VIEWPAGER_TAG = "VIEWPAGER_TAG"
const val TAKE_PHOTO_TAG = "TAKE_PHOTO_TAG"
val PHOTO_CATEGORY = listOf(PHOTO_GENERAL, PHOTO_DETAIL, PHOTO_BLUE)
val PHOTO_NAMES = listOf(PHOTO_GENERAL_NAME, PHOTO_DETAIL_NAME, PHOTO_BLUE_NAME)

// PDF
const val A4_height_px = 1120
const val A4_width_px = 792

val tableList = listOf(
    "Référence ", "Numéro de lot ", "Numéro de BL ", "Localisation ", "Comment", "Quantité pièces NOK ",
    "Quantité échantillon ", "Quantité lot ", "Standard attendu ", "Numéros de série ", "Date code ", "Quantité pièces en stock ","Pourquoi", "Conséquence", "Description du problème")
