package pmdmtarea03.jmpp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsFragment extends Fragment {
    private Switch switchAllowDeletion;
    private RadioGroup radioGroupLanguage;
    private RadioButton radioSpanish, radioEnglish;
    private Button buttonLogout;

    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragment, container, false);

        // Inicializar componentes
        switchAllowDeletion = view.findViewById(R.id.switch_allow_deletion);
        radioGroupLanguage = view.findViewById(R.id.radio_group_language);
        radioSpanish = view.findViewById(R.id.radio_spanish);
        radioEnglish = view.findViewById(R.id.radio_english);
        buttonLogout = view.findViewById(R.id.button_logout);

        // Configurar SharedPreferences
        sharedPreferences = requireContext().getSharedPreferences("Settings", Context.MODE_PRIVATE);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        // Cargar valores guardados
        loadSettings();

        // Guardar estado del Switch
        switchAllowDeletion.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("allowDeletion", isChecked);
            editor.apply();
        });

        // Guardar selección de idioma
        radioGroupLanguage.setOnCheckedChangeListener((group, checkedId) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (checkedId == R.id.radio_spanish) {
                editor.putString("language", "es");
                Toast.makeText(getContext(), "Idioma cambiado a Español", Toast.LENGTH_SHORT).show();
            } else if (checkedId == R.id.radio_english) {
                editor.putString("language", "en");
                Toast.makeText(getContext(), "Language switched to English", Toast.LENGTH_SHORT).show();
            }
            editor.apply();
        });

        // Cerrar sesión
        buttonLogout.setOnClickListener(v -> {
            mAuth.signOut();
            Toast.makeText(getContext(), "Sesión cerrada", Toast.LENGTH_SHORT).show();

            // Redirigir al LoginActivity (por ejemplo)
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            requireActivity().finish();
        });

        return view;
    }

    private void loadSettings() {
        // Cargar estado del Switch
        boolean allowDeletion = sharedPreferences.getBoolean("allowDeletion", false);
        switchAllowDeletion.setChecked(allowDeletion);

        // Cargar idioma seleccionado
        String language = sharedPreferences.getString("language", "es");
        if (language.equals("es")) {
            radioSpanish.setChecked(true);
        } else if (language.equals("en")) {
            radioEnglish.setChecked(true);
        }
    }
}
